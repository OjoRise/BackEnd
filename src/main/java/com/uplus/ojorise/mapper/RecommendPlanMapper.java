package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.RecommendPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecommendPlanMapper {
    RecommendPlan getResult(@Param("id") Long id);
    void insertRecommendPlan(@Param("planList") RecommendPlan planList);
    RecommendPlan maintainRecommendPlan(@Param("id") Long id);
    RecommendPlan deleteRecommendPlan(@Param("id") Long id, @Param("target") String target);
}
