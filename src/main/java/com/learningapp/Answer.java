package com.learningapp;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_alternative", nullable = false, length = Integer.MAX_VALUE)
    private String questionAlternative;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;
    @Column(name = "is_correct")
    private Boolean isCorrect;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getQuestionAlternative() {
        return questionAlternative;
    }

    public void setQuestionAlternative(String questionAlternative) {
        this.questionAlternative = questionAlternative;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
