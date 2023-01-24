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
        "chapter_id",
        "subchapter_id",
        "course_mapped",
        "explanation_active",
        "explanation_active",
        "calculator",
        "image_modifiers",
        "video_id",
        "question_image_id",
        "explanation_image_id",
        "explanation_video_id",
        "answerbit"
})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("id")
    @Column(name = "question_id")
    private int questionId;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Column(columnDefinition = "integer[]")
    private int[] correctAnswerArray;

    private String chapter;

    private String explanation;

    @JsonProperty("IMAGE_NORMAL")
    private String image_normal;

    @JsonProperty("IMAGE_LARGE")
    private String image_large;

    private byte[] image_normal_blob;

    private byte[] image_large_blob;

    public byte[] getImage_normal_blob() {
        return image_normal_blob;
    }

    public void setImage_normal_blob(byte[] image_normal_blob) {
        this.image_normal_blob = image_normal_blob;
    }

    public byte[] getImage_large_blob() {
        return image_large_blob;
    }

    public void setImage_large_blob(byte[] image_large_blob) {
        this.image_large_blob = image_large_blob;
    }

    private int correctAnswer;

    @JsonProperty("question_video_images.thumbnail_295")
    private String questionVideo;

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

    public int[] getCorrectAnswerArray() {
        return correctAnswerArray;
    }

    public void setCorrectAnswerArray(int[] correctAnswerArray) {
        this.correctAnswerArray = correctAnswerArray;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @JsonProperty("id")
    public int getQuestionId() {
        return questionId;
    }

    @JsonProperty("id")
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


}
