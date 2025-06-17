package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.MyPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPlanMapper {
    List<MyPlan> findMyPlanAll();

    List<MyPlan> findMyPlanByName(
            @Param("name") String name
    );
}
