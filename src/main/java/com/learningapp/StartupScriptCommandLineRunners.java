package com.learningapp;

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
//        parseExplanations();
    }


    public void parseExplanations() {

        List<QuestionRepository.QuestionExplanationProjection> allQuestionSummaries = questionRepository.getQuestionsOnlyIdAndExplanation();
        for (QuestionRepository.QuestionExplanationProjection question : allQuestionSummaries) {
            String explanation = question.getExplanation();
            Document doc = Jsoup.parse(explanation);
            Elements images = doc.select("img[srcset]");
            if (images.size() == 0) {
                continue;
            }
            for (Element image : images) {
                image.removeAttr("srcset");
                image.removeAttr("sizes");
                image.removeAttr("alt");
                image.removeAttr("loading");
            }
            Elements svg = doc.select("svg");
            for (Element svgElement : svg) {
                svgElement.remove();
            }
            Elements spans = doc.select("span");
            for (Element span : spans) {
                span.remove();
            }
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                if (link.attr("href")
                        .contains("cdn")) {
                    link.remove();
                }
            }
            String new_explanation_html = doc.body()
                                             .html();
            questionRepository.updateExplanation(question.getId(), new_explanation_html);
            //Extract image URLs
//            Elements imgElements = doc.select("img");
//            for (Element imgElement : imgElements) {
//                String s = imgElement.attr("srcset");
//
//                int firstCommaIndex = s.indexOf(",");
//                int firstUrlLastSpace = s.substring(0,firstCommaIndex).lastIndexOf(" ");
//                int secondUrlLastSpace = s.substring(firstCommaIndex + 2).lastIndexOf(" ");
//
//                String firstUrl = s.substring(0, firstUrlLastSpace);
//                String secondUrl = s.substring(firstCommaIndex+2,firstCommaIndex+2+secondUrlLastSpace);
//                questionRepository.updateExplanationImageNormal(question.getId(), retrieveImage(firstUrl));
//                questionRepository.updateExplanationImageLarge(question.getId(), retrieveImage(secondUrl));
//
//            }

            //Remove anchor tags related to sound
//            Elements soundElements = doc.select(".sm2_button");
//            for (Element soundElement : soundElements) {
//                soundElement.remove();
//            }
//
//            String new_explanation = doc.body().html();

//            questionRepository.updateExplanation(question.getId(), new_explanation);
        }
    }

    // Migrate images from CDN to database, hopefully only need once, rewrite to
    // exclude already migrated images if run again
    public void migrateImages() {
        Iterable<Question> questions = questionRepository.findAll();
        for (Question question : questions) {
            if (question.getImageNormal() != null && !question.getImageNormal()
                                                              .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImageNormal());
                question.setImageNormalBlob(imageBytes);
//                question.setImage_normal(null);
            }
            if (question.getImageLarge() != null && !question.getImageLarge()
                                                             .isEmpty()) {
                byte[] imageBytes = retrieveImage(question.getImageLarge());
                question.setImageLargeBlob(imageBytes);
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
                if (!existingQuestionIds.contains(question.getExternalQuestionId())) {
                    uniqueQuestions.add(question);
                    existingQuestionIds.add(question.getExternalQuestionId());
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
