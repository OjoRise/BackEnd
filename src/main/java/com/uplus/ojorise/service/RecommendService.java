package com.uplus.ojorise.service;

import com.uplus.ojorise.mapper.RecommendMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final RecommendMapper recommendMapper;
    private final JwtUtil jwtUtil;

    public void saveRecommendService(String accessToken, String[] recommendPlans) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        for(String recommendPlan : recommendPlans) {
            recommendMapper.
        }

    }
}
