package com.example.testtemplate;

import jakarta.persistence.*;
import java.util.Map;

@Embeddable
public class Answerbit {
    public Map<Integer, String> getBitstr() {
        return bitstr;
    }

    public void setBitstr(Map<Integer, String> bitstr) {
        this.bitstr = bitstr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ElementCollection
    private Map<Integer, String> bitstr;

    private String type;
}
