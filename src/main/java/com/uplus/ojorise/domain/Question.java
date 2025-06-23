package com.uplus.ojorise.domain;

import lombok.Data;

@Data
public class Question {
    private int question_id;
    private String question_title;
    private String answer_one;
    private String answer_two;
}
