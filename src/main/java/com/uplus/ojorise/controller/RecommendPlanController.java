package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.dto.RecommendPlanRequest;
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

    @PostMapping
    @Operation(
            summary = "추천 요금제 동기화",
            description = "로그인 후 로컬에 저장된 추천 요금제(planId 리스트)를 DB에 저장합니다. 이미 존재하는 추천은 중복 저장되지 않습니다."
    )
    public ResponseEntity<String> syncRecommendations(@RequestBody RecommendPlanRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        recommendPlanService.addAllIfNotExists(userId.intValue(), request.getPlanIds());

        return ResponseEntity.ok("추천 요금제가 성공적으로 동기화되었습니다.");
    }
}

