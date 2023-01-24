package com.example.testtemplate;

//import org.springframework.security.core.Authentication;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private final QuestionRepository questionRepository;

    public IndexController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/quiz")
    public String displayQuiz(Model model) {
        Question question = questionRepository.findRandom1();
        model.addAttribute("question", question);
        return "quiz";
    }

    @PostMapping("/submit_answer")
    public String newQuestion(Model model) {
        Question question = questionRepository.findRandom1();
        model.addAttribute("question", question);
        return "quiz :: question";
    }

    @GetMapping(value = "/question/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getQuestionImage(@PathVariable Long id) {
        Question question = questionRepository.findById(id).get();
        return question.getImage_normal_blob();
    }


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
