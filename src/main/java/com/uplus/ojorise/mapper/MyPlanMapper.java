package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.MyPlan;
import com.uplus.ojorise.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyPlanMapper {
    MyPlan findMyPlanById(@Param("id") Long id);

    Plan findMyPlanByName(
            @Param("planName") String planName,
            @Param("telecomProvider") String telecomProvider
    );
}
