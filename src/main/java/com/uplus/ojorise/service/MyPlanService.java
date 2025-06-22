package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.MyPlan;
import com.uplus.ojorise.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.uplus.ojorise.mapper.MyPlanMapper;

@Service
@RequiredArgsConstructor
public class MyPlanService {

    private final MyPlanMapper myPlanMapper;

    public MyPlan findMyPlanById(Long id) {
        return myPlanMapper.findMyPlanById(id);
    }

    public Plan findMyPlanByName(MyPlan myPlan) {
        return myPlanMapper.findMyPlanByName(myPlan.getPlanName(), myPlan.getTelecomProvider());
    }
}
