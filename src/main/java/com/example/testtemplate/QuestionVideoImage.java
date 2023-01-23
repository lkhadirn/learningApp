package com.example.testtemplate;

import jakarta.persistence.*;

@Entity
public class QuestionVideoImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int imageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    private String src;
}
