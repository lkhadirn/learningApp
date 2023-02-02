package com.learningapp;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LearningAppController {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuizResultRepository quizResultRepository;

    public LearningAppController(QuestionRepository questionRepository, UserRepository userRepository, QuizResultRepository quizResultRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.quizResultRepository = quizResultRepository;
    }

    @GetMapping("/quiz")
    public String displayQuiz(Model model) {
        Question question = questionRepository.findRandom1();
        model.addAttribute("question", question);
        return "quiz";
    }

    @PostMapping("/submit_answer")
    public String newQuestion(Model model, Principal principal) {
        Question question = questionRepository.findRandom1();
        model.addAttribute("question", question);

        if (principal != null) {
            User user = userRepository.findByEmail(principal.getName());
            QuizResult quizResult = new QuizResult();
            quizResult.setUser(user);
            quizResult.setQuestion(question);

            // TODO: implement logic to set the value of correct based on user's answer

            quizResultRepository.save(quizResult);
        }

        return "quiz :: question";
    }

    @GetMapping(value = "/question/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getQuestionImage(@PathVariable Long id) {
        Question question = questionRepository.findById(id)
                                              .get();
        return question.getImageNormalBlob();
    }

    @GetMapping(value = "/question/explanation-image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getQuestionExplanationImage(@PathVariable Long id) {
        Question question = questionRepository.findById(id)
                                              .get();
        return question.getExplanationImageLargeBlob();
    }


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
