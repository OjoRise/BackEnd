package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Survey;
import com.uplus.ojorise.dto.SurveyResponse;
import com.uplus.ojorise.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    @Operation(summary = "설문 결과 저장", description = "survey 테이블에 설문 결과를 저장합니다.")
    @PostMapping
    public ResponseEntity<?> saveSurvey(@RequestBody SurveyResponse dto,
                                        HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");

            Survey survey = Survey.builder()
                    .birthdate(dto.getBirthdate())
                    .telecomProvider(dto.getTelecomProvider())
                    .planName(dto.getPlanName())
                    .contractTerm(dto.getContractTerm())
                    .familyBundle(dto.getFamilyBundle())
                    .build();

            surveyService.saveSurvey(accessToken, survey);
            return ResponseEntity.ok().body("설문 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("설문 저장 실패: " + e.getMessage());
        }
    }
}
