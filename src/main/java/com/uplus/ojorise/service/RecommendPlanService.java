package com.uplus.ojorise.service;

import com.uplus.ojorise.dto.Plan;
import com.uplus.ojorise.dto.RecommendPlan;
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
}
