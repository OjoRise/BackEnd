package com.uplus.ojorise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.uplus.ojorise.domain.MyPlan;
import com.uplus.ojorise.mapper.MyPlanMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPlanService {

    private final MyPlanMapper myPlanMapper;

    public List<MyPlan> findMyPlanAll() {
        return myPlanMapper.findMyPlanAll();
    }

    public List<MyPlan> findMyPlanByName(String name) {
        return myPlanMapper.findMyPlanByName(name);
    }
}
