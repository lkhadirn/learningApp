package com.learningapp;

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
    // Most images around 470 x 360, but some variation, mostly in height
    private byte[] imageNormalBlob;
    // Most images around 1024 x 680, most variation in height
    private byte[] imageLargeBlob;
    private byte[] explanationImageNormalBlob;
    private byte[] explanationImageLargeBlob;
    @Column(name = "external_question_id", nullable = false)
    private Integer externalQuestionId;

    public Question() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getExternalQuestionId() {
        return externalQuestionId;
    }

    public void setExternalQuestionId(Integer externalQuestionId) {
        this.externalQuestionId = externalQuestionId;
    }

    public byte[] getExplanationImageNormalBlob() {
        return explanationImageNormalBlob;
    }

    public void setExplanationImageNormalBlob(byte[] explanationImageNormalBlob) {
        this.explanationImageNormalBlob = explanationImageNormalBlob;
    }

    public byte[] getExplanationImageLargeBlob() {
        return explanationImageLargeBlob;
    }

    public void setExplanationImageLargeBlob(byte[] explanationImageLargeBlob) {
        this.explanationImageLargeBlob = explanationImageLargeBlob;
    }

    public byte[] getImageNormalBlob() {
        return imageNormalBlob;
    }

    public void setImageNormalBlob(byte[] imageNormalBlob) {
        this.imageNormalBlob = imageNormalBlob;
    }

    public byte[] getImageLargeBlob() {
        return imageLargeBlob;
    }

    public void setImageLargeBlob(byte[] imageLargeBlob) {
        this.imageLargeBlob = imageLargeBlob;
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


}
