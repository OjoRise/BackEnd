package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatusMapper {
    List<Plan> findPlanByBirthDate(
            @Param("eligibilityList") List<String> eligibilityList
    );
    
    List<Plan> findAllPlansForLG();

    Plan findPlanByPlanName(String planName);
}
