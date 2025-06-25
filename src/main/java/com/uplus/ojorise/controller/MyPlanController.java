package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.MyPlan;
import com.uplus.ojorise.domain.Plan;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.uplus.ojorise.service.MyPlanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPlan")
public class MyPlanController {

    private final MyPlanService myPlanService;

    @GetMapping
    @Operation(summary = "나의 요금제 조회", description = "사용자의 요금제 이름을 불러옵니다.")
    public ResponseEntity<Plan> getMyPlanByName(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();

        MyPlan myPlan = myPlanService.findMyPlanById(userId);
        if(myPlan == null){
            throw new RuntimeException("사용자의 설문 결과가 존재하지 않습니다.");
        }

        Plan plan = myPlanService.findMyPlanByName(myPlan);
        if(plan == null){
            throw new RuntimeException("요금제가 존재하지 않습니다.");
        }

        return ResponseEntity.ok(plan);
    }

    @PostMapping("/guest")
    @Operation(summary = "게스트의 나의 요금제 조회", description = "게스트의 요금제를 불러옵니다.")
    public ResponseEntity<Plan> getMyPlanByName(@RequestBody MyPlan myPlan) {
        Plan plan = myPlanService.findMyPlanByName(myPlan);
        if(plan == null){
            throw new RuntimeException("요금제가 존재하지 않습니다.");
        }

        return ResponseEntity.ok(plan);
    }
}