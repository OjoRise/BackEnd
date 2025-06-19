package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.TongBTI;
import com.uplus.ojorise.dto.TongBTIPlanResponse;
import com.uplus.ojorise.dto.TongBTIRequest;
import com.uplus.ojorise.service.TongBTIService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tongbti")
@RequiredArgsConstructor
public class TongBTIController {
    private final TongBTIService tongBTIService;

    @Operation(summary = "통BTI 결과 조회", description = "accessToken을 기반으로 tongBTI 테이블에서 해당 유저의 결과를 조회합니다.")
    @GetMapping("/result")
    public ResponseEntity<?> getTongResult(HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");
            TongBTI result = tongBTIService.getTongResult(accessToken);

            if (result == null) {
                return ResponseEntity.ok(Map.of("tongResult", ""));
            }

            return ResponseEntity.ok(Map.of("tongResult", result.getTongName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회 실패: " + e.getMessage());
        }
    }

    @Operation(
            summary = "통BTI 결과 저장",
            description = "프론트에서 계산된 통BTI 유형(tongName)을 받아서, 해당 유저의 결과를 저장합니다."
    )
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTongBTI(@RequestBody TongBTIRequest request, Authentication auth) {
        String tongName = request.getTongName();
        if (tongName == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing tongName"));
        }

        Long userId = (Long) auth.getPrincipal();
        Long tongId = tongBTIService.saveTongBTI(userId, tongName);

        return ResponseEntity.ok(Map.of("id", tongId, "tongName", tongName));
    }

    @Operation(summary = "통BTI 유형으로 요금제 상세 조회", description = "tongName을 기반으로 통BTI 설명 + 요금제 상세 정보를 조회합니다.")
    @GetMapping("/info")
    public ResponseEntity<?> getTongBTIInfo(@RequestParam String tongName) {
        try {
            TongBTIPlanResponse response = tongBTIService.getTongBTIWithPlanInfo(tongName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회 실패: " + e.getMessage());
        }
    }

}
