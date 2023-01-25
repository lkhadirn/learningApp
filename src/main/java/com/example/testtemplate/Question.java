package com.example.testtemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Answer> answers;

    private String chapter;

    private String explanation;

    private String image_normal;

    private String image_large;

    private byte[] image_normal_blob;

    private byte[] image_large_blob;

    private byte[] explanation_image_normal_blob;

    private byte[] explanation_image_large_blob;

    @Column(name = "external_question_id", nullable = false)
    private Integer externalQuestionId;

    public Question() {
    }

    public Integer getExternalQuestionId() {
        return externalQuestionId;
    }

    public void setExternalQuestionId(Integer externalQuestionId) {
        this.externalQuestionId = externalQuestionId;
    }

    public byte[] getExplanation_image_normal_blob() {
        return explanation_image_normal_blob;
    }

    public void setExplanation_image_normal_blob(byte[] explanation_image_normal_blob) {
        this.explanation_image_normal_blob = explanation_image_normal_blob;
    }

    public byte[] getExplanation_image_large_blob() {
        return explanation_image_large_blob;
    }

    public void setExplanation_image_large_blob(byte[] explanation_image_large_blob) {
        this.explanation_image_large_blob = explanation_image_large_blob;
    }

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

}
