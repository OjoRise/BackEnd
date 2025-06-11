package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.mapper.StatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final StatusMapper statusMapper;

    public List<Plan> findPlanByPropsWithName(List<String> eligibilityList, List<String> telecomProviderList, String name) {
        return statusMapper.findPlanByPropsWithName(eligibilityList, telecomProviderList, name);
    }

    public List<Plan> findPlanByPropsWithoutName(List<String> eligibilityList, List<String> telecomProviderList) {
        return statusMapper.findPlanByPropsWithoutName(eligibilityList, telecomProviderList);
    }
}
