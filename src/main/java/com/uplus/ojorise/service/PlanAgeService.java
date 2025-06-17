package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.PlanAge;
import com.uplus.ojorise.mapper.PlanAgeMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanAgeService {
    private final PlanAgeMapper planAgeMapper;
    private final JwtUtil jwtUtil;

    public PlanAge getPlanAgeResult(String accessToken) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        return planAgeMapper.getResult(userId);
    }

    public void insertPlanAge(String accessToken, String age) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        PlanAge planAge = new PlanAge();
        planAge.setId(userId);
        planAge.setPlanAgeResult(age);
        planAgeMapper.insertPlanAge(planAge);
    }
}


