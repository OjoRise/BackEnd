package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.TongBTI;
import com.uplus.ojorise.service.TongBTIService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tongbti")
@RequiredArgsConstructor
public class TongBTIController {
    private final TongBTIService tongBTIService;

    @Operation(summary = "통BTI 결과 조회", description = "accessToken을 기반으로 tong_bti 테이블에서 해당 유저의 결과를 조회합니다.")
    @GetMapping("/result")
    public ResponseEntity<?> getTongResult(HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");
            TongBTI result = tongBTIService.getTongResult(accessToken);

            if (result == null) {
                return ResponseEntity.status(404).body("해당 유저의 통BTI 결과가 없습니다.");
            }

            return ResponseEntity.ok(result.getTongResult());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회 실패: " + e.getMessage());
        }
    }
}
