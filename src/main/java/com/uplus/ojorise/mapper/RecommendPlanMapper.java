package com.uplus.ojorise.mapper;

import com.uplus.ojorise.dto.Plan;
import com.uplus.ojorise.dto.RecommendPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RecommendPlanMapper {
    @Select("""
    SELECT p.*
    FROM recommendplan r
    JOIN plan p ON r.plan_id = p.plan_id
    WHERE r.id = #{id}
""")
    List<Plan> findByUserId(int id);

    @Delete("DELETE FROM recommendplan WHERE id = #{id} AND plan_id = #{planId}")
    void delete(@Param("id") int id, @Param("planId") int planId);
}
