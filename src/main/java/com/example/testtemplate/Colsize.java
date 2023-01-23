package com.example.testtemplate;

import jakarta.persistence.*;

@Embeddable
public class Colsize {

    private int question;

    private int image;

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
