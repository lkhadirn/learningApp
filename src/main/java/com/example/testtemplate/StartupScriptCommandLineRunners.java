package com.example.testtemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * This class is used to load the questions from the json file into the database
 * Also used just in general to run "one time jobs" that need the Spring context
 */
@Component
public class StartupScriptCommandLineRunners implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
//        import_questions_to_database();
//        migrateImages();
        parseExplanations();
    }


    public void parseExplanations() {

        List<QuestionRepository.QuestionExplanationProjection> allQuestionSummaries = questionRepository.getQuestionsOnlyIdAndExplanation();
        for (QuestionRepository.QuestionExplanationProjection question : allQuestionSummaries) {
            String explanation = question.getExplanation();
            Document doc = Jsoup.parse(explanation);

            //Extract image URLs
            Elements imgElements = doc.select("img");
            for (Element imgElement : imgElements) {
                String imageNormal = imgElement.attr("srcset");
                String imageLarge = imgElement.attr("src");
//                question.setExplanationImageNormal(imageNormal);
//                question.setExplanationImageLarge(imageLarge);
            }

            //Remove anchor tags related to sound
            Elements soundElements = doc.select(".sm2_button");
            for (Element soundElement : soundElements) {
                soundElement.remove();
            }

            String new_explanation = doc.html();
            questionRepository.updateExplanation(question.getId(), new_explanation);
        }
    }

    // Migrate images from CDN to database, hopefully only need once, rewrite to
    // exclude already migrated images if run again
    public void migrateImages() {
        Iterable<Question> questions = questionRepository.findAll();
        for (Question question : questions) {
            if (question.getImage_normal() != null && !question.getImage_normal()
                                                               .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImage_normal());
                question.setImage_normal_blob(imageBytes);
//                question.setImage_normal(null);
            }
            if (question.getImage_large() != null && !question.getImage_large()
                                                              .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImage_large());
                question.setImage_large_blob(imageBytes);
//                question.setImage_large(null);
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


    // Import questions retrieved from external service, should be run only once
    private void import_questions_to_database() throws IOException {
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
        if (files == null) {
            return new ArrayList<>();
        }
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
