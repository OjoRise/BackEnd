package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.service.DipPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dips")
@Tag(name = "찜한 요금제", description = "사용자별 찜한 요금제 관련 API")
public class DipPlanController {

    private final DipPlanService dipPlanService;

    @GetMapping
    @Operation(summary = "찜한 요금제 조회", description = "로그인한 사용자의 찜한 요금제를 조회합니다.")
    public ResponseEntity<List<Plan>> getDippedPlans(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(dipPlanService.getByUserId(userId.intValue()));
    }
    
    @PostMapping("/{planId}")
    @Operation(summary = "찜한 요금제 추가 및 삭제", description = "사용자가 찜한 특정 요금제를 추가 및 삭제합니다.")
    public ResponseEntity<String> checkDipPlan(@PathVariable int planId, Authentication authentication) {

            Long userId = (Long) authentication.getPrincipal();

            List<Integer> findPlan = dipPlanService.findByUserIdOnlyPlanId(userId.intValue());

            if (findPlan.contains(planId)) {
                dipPlanService.delete(userId.intValue(), planId);
                return ResponseEntity.ok("찜한 요금제가 성공적으로 삭제되었습니다.");
            }

            dipPlanService.insert(userId.intValue(), planId);
            return ResponseEntity.ok("찜한 요금제가 성공적으로 추가되었습니다.");
        }
    }