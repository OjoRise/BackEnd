package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Question;
import com.uplus.ojorise.domain.TongBTI;
import com.uplus.ojorise.service.QuestionService;
import com.uplus.ojorise.service.TongBTIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    @Operation(summary = "질문 목록 조회", description = "통bti 모든 질문과 선택지를 반환합니다.")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
}
