package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.AgeResult;
import com.uplus.ojorise.domain.BrowsePlan;
import com.uplus.ojorise.domain.PlanAge;
import com.uplus.ojorise.dto.PlanAgeResponse;
import com.uplus.ojorise.service.BrowsePlanService;
import com.uplus.ojorise.service.PlanAgeService;
import com.uplus.ojorise.service.RecommendPlanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/age")
@RequiredArgsConstructor
public class PlanAgeController {
    private final PlanAgeService planAgeService;
    private final RecommendPlanService recommendPlanService;
    private final BrowsePlanService browsePlanService;

    @Operation(summary = "요금제 나이 테스트 결과 조회", description = "accessToken을 기반으로 해당 유저의 결과를 조회합니다.")
    @GetMapping("/result")
    public ResponseEntity<?> getAge(HttpServletRequest request) {
        try {
            String accessToken = (String) request.getAttribute("accessToken");
            PlanAge result = planAgeService.getAge(accessToken);

            if (result == null) {
                return ResponseEntity.ok(Map.of("age", ""));
            }

            return ResponseEntity.ok(Map.of("age", result.getAge()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회 실패: " + e.getMessage());
        }
    }

    @Operation(summary = "요금제 나이 테스트 결과", description = "query param을 기준으로 결과를 return합니다.")
    @GetMapping
    public ResponseEntity<?> getPlanAge(@RequestParam(name="age") String age, @RequestParam(name="result") String result) {
        try {
            AgeResult realAge = planAgeService.getResult(age);
            AgeResult resultAge = planAgeService.getResult(result);
            System.out.println("REAL: "+realAge.getDescription()+realAge.getRecommend());
            BrowsePlan recommendPlan = browsePlanService.getBrowsePlanById(realAge.getRecommend());

            return ResponseEntity.ok(Map.of("result",resultAge, "recommendPlan",recommendPlan));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @Operation(summary = "통신 연령 결과 저장", description = "planAge 테이블에 통신 연령 결과 저장")
    @PostMapping("/result")
    public ResponseEntity<String> postPlanAgeResult(@RequestBody PlanAgeResponse dto, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        try {
            planAgeService.insertPlanAge(userId.intValue(),dto.getAge());
            recommendPlanService.addAllIfNotExists(userId.intValue(),dto.getRecommendedPlan());
            return ResponseEntity.ok().body("통신 연령 저장 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("통신 연령 저장 실패: " + e.getMessage());
        }
    }
}


