package com.uplus.ojorise.domain;

import lombok.Data;

@Data
public class Question {
    private int questionId;
    private String questionTitle;
    private String answerOne;
    private String answerTwo;
}
