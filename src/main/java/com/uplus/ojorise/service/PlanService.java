package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.mapper.StatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final StatusMapper statusMapper;

    public List<Plan> findPlanByBirthDate(List<String> eligibilityList) {
        return statusMapper.findPlanByBirthDate(eligibilityList);
    }
    
    public List<Plan> findAllPlansForLG() {
        return statusMapper.findAllPlansForLG();
    }

    public List<Plan> findPlanByPlanName(String[] planIds) {
        List<Plan> planList = new ArrayList<>();
        for (String planId : planIds) {
            planList.add(statusMapper.findPlanByPlanName(planId));
        }
        return planList;
    }

}
