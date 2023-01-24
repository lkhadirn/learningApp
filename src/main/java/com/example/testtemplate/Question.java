package com.example.testtemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"colsize",
        "question_type",
        "answered",
        "already_answered",
        "server_saved",
        "user_answer",
        "user_answer_array",
        "question_video_images",
        "answerbit"
})
public class Question {

    @Id
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @ElementCollection
    private List<Integer> correctAnswerArray;

    private String chapter;

    private int chapterId;

    private int subchapterId;

    private boolean courseMapped;

    private String explanation;

    private int explanationActive;

    private int calculator;

    private int questionImageId;

    @JsonProperty("IMAGE_NORMAL")
    private String image_normal;

    @JsonProperty("IMAGE_LARGE")
    private String image_large;

    private int explanationImageId;

    private int explanationVideoId;

    private int correctAnswer;

    private int videoId;

    @JsonProperty("question_video_images.thumbnail_295")
    private String questionVideo;

    private String imageModifiers;

    public String getImage_normal() {
        return image_normal;
    }

    public void setImage_normal(String image_normal) {
        this.image_normal = image_normal;
    }

    public String getImage_large() {
        return image_large;
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    public String getQuestionVideo() {
        return questionVideo;
    }

    public void setQuestionVideo(String questionVideo) {
        this.questionVideo = questionVideo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    public List<Integer> getCorrectAnswerArray() {
        return correctAnswerArray;
    }

    public void setCorrectAnswerArray(List<Integer> correctAnswerArray) {
        this.correctAnswerArray = correctAnswerArray;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getSubchapterId() {
        return subchapterId;
    }

    public void setSubchapterId(int subchapterId) {
        this.subchapterId = subchapterId;
    }

    public boolean isCourseMapped() {
        return courseMapped;
    }

    public void setCourseMapped(boolean courseMapped) {
        this.courseMapped = courseMapped;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getExplanationActive() {
        return explanationActive;
    }

    public void setExplanationActive(int explanationActive) {
        this.explanationActive = explanationActive;
    }

    public int getCalculator() {
        return calculator;
    }

    public void setCalculator(int calculator) {
        this.calculator = calculator;
    }


    public int getQuestionImageId() {
        return questionImageId;
    }

    public void setQuestionImageId(int questionImageId) {
        this.questionImageId = questionImageId;
    }

    public String getImageNormal() {
        return image_normal;
    }

    public void setImageNormal(String IMAGE_NORMAL) {
        this.image_normal = IMAGE_NORMAL;
    }

    public String getImageLarge() {
        return image_large;
    }

    public void setImageLarge(String IMAGE_LARGE) {
        this.image_large = IMAGE_LARGE;
    }

    public int getExplanationImageId() {
        return explanationImageId;
    }

    public void setExplanationImageId(int explanationImageId) {
        this.explanationImageId = explanationImageId;
    }

    public int getExplanationVideoId() {
        return explanationVideoId;
    }

    public void setExplanationVideoId(int explanationVideoId) {
        this.explanationVideoId = explanationVideoId;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getImageModifiers() {
        return imageModifiers;
    }

    public void setImageModifiers(String imageModifiers) {
        this.imageModifiers = imageModifiers;
    }
}
