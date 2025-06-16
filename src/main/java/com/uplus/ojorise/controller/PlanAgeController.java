package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.PlanAge;
import com.uplus.ojorise.service.PlanAgeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/planage")
@RequiredArgsConstructor
public class PlanAgeController {
    private final PlanAgeService planAgeService;

    @Operation(summary = "요금제 나이 테스트 결과 조회", description = "accessToken을 기반으로 해당 유저의 결과를 조회합니다.")
    @GetMapping("/result")
    public ResponseEntity<?> getPlanAgeResult(HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");
            PlanAge result = planAgeService.getPlanAgeResult(accessToken);

            if (result == null) {
                return ResponseEntity.status(404).body("해당 유저의 요금제 나이 테스트 결과가 없습니다.");
            }

            return ResponseEntity.ok(Map.of("planAgeResult", result.getPlanAgeResult()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회 실패: " + e.getMessage());
        }
    }
}


