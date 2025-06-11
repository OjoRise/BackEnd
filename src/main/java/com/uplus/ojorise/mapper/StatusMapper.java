package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatusMapper {
    List<Plan> findPlanByPropsWithName(
            @Param("eligibilityList") List<String> eligibilityList,
            @Param("telecomProviderList") List<String> telecomProviderList,
            @Param("name") String name
    );
    List<Plan> findPlanByPropsWithoutName(
            @Param("eligibilityList") List<String> eligibilityList,
            @Param("telecomProviderList") List<String> telecomProviderList
    );
}
