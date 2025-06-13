package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.RecommendedPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendMapper {
    String[] findRecommendedPlanByUserId(Long userId);
    void insertRecommendedPlan(String[] recommendPlan);
}
