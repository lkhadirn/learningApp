package com.example.testtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

@Component
public class JsonFileReader implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = objectMapper.readValue(new File("src/main/resources/questions.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
        questionRepository.saveAll(questions);
    }
}
