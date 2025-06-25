package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Question;
import com.uplus.ojorise.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;

    public List<Question> getAllQuestions() {
        System.out.println("getAllQuestions: " + questionMapper.findAll());
        return questionMapper.findAll();
    }
}
