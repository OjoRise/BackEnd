package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.RecommendPlan;
import com.uplus.ojorise.mapper.RecommendPlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendPlanService {

    private final RecommendPlanMapper recommendPlanMapper;

    public List<Plan> getByUserId(int id) {
        return recommendPlanMapper.findByUserId(id);
    }

    public void delete(int id, int planId) {
        recommendPlanMapper.delete(id, planId);
    }

    public void addIfNotExists(int id, String planName) {
        Integer planId = recommendPlanMapper.findPlanIdByName(planName);
        if (planId != null && !recommendPlanMapper.exists(id, planId)) {
            RecommendPlan recommend = new RecommendPlan();
            recommend.setId(id);
            recommend.setPlanId(planId);
            recommendPlanMapper.insert(recommend);
        }
    }


    public void addAllIfNotExists(int id, List<String> planNames) {
        for (String name : planNames) {
            addIfNotExists(id, name);
        }
    }
}
