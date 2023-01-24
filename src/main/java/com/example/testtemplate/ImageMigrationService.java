package com.example.testtemplate;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImageMigrationService {

    private final QuestionRepository questionRepository;

    public ImageMigrationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void migrateImages() {
        Iterable<Question> questions = questionRepository.findAll();
        for (Question question : questions) {
            if (question.getImage_normal() != null && !question.getImage_normal()
                                                               .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImage_normal());
                question.setImage_normal_blob(imageBytes);
                question.setImage_normal(null);
            }
            if (question.getImage_large() != null && !question.getImage_large()
                                                              .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImage_large());
                question.setImage_large_blob(imageBytes);
                question.setImage_large(null);
            }
        }
        questionRepository.saveAll(questions);
    }

    private byte[] retrieveImage(String imageUrl) {
        try {
            byte[] imageBytes;
            try (InputStream inputStream = new URL(imageUrl).openStream();
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                inputStream.transferTo(outputStream);
                imageBytes = outputStream.toByteArray();
                return imageBytes;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
