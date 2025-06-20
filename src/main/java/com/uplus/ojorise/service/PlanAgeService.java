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

    public PlanAge getAge(String accessToken) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        return planAgeMapper.getResult(userId);
    }

    public void insertPlanAge(int userId, String age) {

        PlanAge planAge = new PlanAge();
        planAge.setId(userId);
        planAge.setAge(age);
        planAgeMapper.insertPlanAge(planAge);
    }
}


