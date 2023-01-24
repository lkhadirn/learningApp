package com.example.testtemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JsonFileReader implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Integer> existingQuestionIds = new HashSet<>(questionRepository.findAllQuestionIds());
        List<String> filePathsList = getFilePaths();
        for (String filePath : filePathsList) {
            List<Question> questions = objectMapper.readValue(new File(filePath),
                    objectMapper.getTypeFactory()
                                .constructCollectionType(List.class, Question.class));
            List<Question> uniqueQuestions = new ArrayList<>();
            for (Question question : questions) {
                if (!existingQuestionIds.contains(question.getQuestionId())) {
                    uniqueQuestions.add(question);
                    existingQuestionIds.add(question.getQuestionId());
                } else {
                    System.out.println("Question already exists: " + question.getQuestion());
                }
            }
            questionRepository.saveAll(uniqueQuestions);
        }
    }

    public List<String> getFilePaths() {
        File folder = new File("/Users/gregtaube/Downloads/");
        File[] files = folder.listFiles();
        List<String> filePaths = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName()
                                     .startsWith("questions")) {
                filePaths.add(file.getPath());
            }
        }
        return filePaths;
    }


}
