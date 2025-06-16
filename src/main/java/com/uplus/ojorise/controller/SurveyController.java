package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.Survey;
import com.uplus.ojorise.dto.PlanResponse;
import com.uplus.ojorise.dto.SurveyResponse;
import com.uplus.ojorise.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

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
                    .planPrice(dto.getPlanPrice())
                    .familyBundle(dto.getFamilyBundle())
                    .familyNum(dto.getFamilyNum())
                    .build();

            surveyService.saveSurvey(accessToken, survey);
            return ResponseEntity.ok().body("설문 저장 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("설문 저장 실패: " + e.getMessage());
        }
    }

    @Operation(summary = "통신사 기반 요금제 조회", description = "요청한 통신사에 해당하는 요금제 목록을 반환합니다.")
    @GetMapping
    public ResponseEntity<List<PlanResponse>> getPlansByProvider(@RequestParam("telecom_provider") String telecomProvider) {
        List<Plan> plans = surveyService.getPlansByTelecomProvider(telecomProvider);

        List<PlanResponse> summaries = plans.stream()
                .map(p -> new PlanResponse(p.getPlanId(), p.getName()))
                .toList();

        return ResponseEntity.ok(summaries);
    }

    @Operation(summary = "설문 결과 조회", description = "설문 결과를 조회합니다.")
    @GetMapping("/result")
    public ResponseEntity<?> getSurveyResult(HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");
            Survey survey = surveyService.getSurvey(accessToken);

            SurveyResponse result = SurveyResponse.builder()
                    .birthdate(survey.getBirthdate())
                    .telecomProvider(survey.getTelecomProvider())
                    .planName(survey.getPlanName())
                    .planPrice(survey.getPlanPrice())
                    .familyBundle(survey.getFamilyBundle())
                    .familyNum(survey.getFamilyNum())
                    .build();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("설문 조회 실패: " + e.getMessage());
        }
    }

    @Operation(summary = "설문 결과 수정", description = "설문 결과를 수정합니다.")
    @PatchMapping
    public ResponseEntity<?> updateSurvey(@RequestBody SurveyResponse dto,
                                          HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");

            Survey survey = Survey.builder()
                    .birthdate(dto.getBirthdate())
                    .telecomProvider(dto.getTelecomProvider())
                    .planName(dto.getPlanName())
                    .planPrice(dto.getPlanPrice())
                    .familyBundle(dto.getFamilyBundle())
                    .familyNum(dto.getFamilyNum())
                    .build();

            surveyService.updateSurvey(accessToken, survey);
            return ResponseEntity.ok().body("설문 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("설문 수정 실패: " + e.getMessage());
        }
    }

}
