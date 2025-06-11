package com.uplus.ojorise.controller;

import com.uplus.ojorise.dto.Plan;
import com.uplus.ojorise.dto.RecommendPlan;
import com.uplus.ojorise.service.RecommendPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
@Tag(name = "추천 요금제", description = "사용자별 추천 요금제 관련 API")
public class RecommendPlanController {

    private final RecommendPlanService recommendPlanService;

    @GetMapping
    @Operation(summary = "추천 요금제 조회", description = "사용자의 추천 요금제 목록을 조회합니다.")
    public ResponseEntity<List<Plan>> getRecommendations(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(recommendPlanService.getByUserId(userId.intValue()));
    }

    @DeleteMapping("/{planId}")
    @Operation(summary = "추천 요금제 삭제", description = "사용자에게 추천된 특정 요금제를 삭제합니다.")
    public ResponseEntity<String> deleteRecommendation(@PathVariable int planId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        recommendPlanService.delete(userId.intValue(), planId);
        return ResponseEntity.ok("추천 요금제가 성공적으로 삭제되었습니다.");
    }
}
