package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.PlanAge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlanAgeMapper {
    PlanAge getResult(@Param("id") Long id);
    void insertPlanAge(@Param("planAge") PlanAge planAge);
}