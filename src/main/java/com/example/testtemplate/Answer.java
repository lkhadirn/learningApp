package com.example.testtemplate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int isCorrect;

    private String questionAlternative;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int key) {
        this.isCorrect = key;
    }

    public String getQuestionAlternative() {
        return questionAlternative;
    }

    public void setQuestionAlternative(String value) {
        this.questionAlternative = value;
    }
}
