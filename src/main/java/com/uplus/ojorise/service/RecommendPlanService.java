package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.RecommendPlan;
import com.uplus.ojorise.mapper.RecommendPlanMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendPlanService {
    private final RecommendPlanMapper recommendPlanMapper;
    private final JwtUtil jwtUtil;

    public RecommendPlan getRecommendPlan(String accessToken) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        return recommendPlanMapper.getResult(userId);
    }

    public RecommendPlan insertRecommendPlan(String accessToken, String [] planList) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        RecommendPlan recommendPlan = new RecommendPlan();
        recommendPlan.setId(userId);
        recommendPlan.setPlanName(planList);
        recommendPlanMapper.insertRecommendPlan(recommendPlan);
        return recommendPlanMapper.maintainRecommendPlan(userId);
    }

    public RecommendPlan deleteRecommendPlan(String accessToken, String target) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        return recommendPlanMapper.deleteRecommendPlan(userId, target);
    }
}
