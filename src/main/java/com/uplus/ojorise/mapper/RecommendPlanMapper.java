package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.RecommendPlan;
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

    @Insert("""
    INSERT INTO recommendplan (plan_id, id)
    VALUES (#{planId}, #{id})
    """)
    void insert(RecommendPlan recommendPlan);

    @Delete("DELETE FROM recommendplan WHERE id = #{id} AND plan_id = #{planId}")
    void delete(@Param("id") int id, @Param("planId") int planId);

    @Select("""
    SELECT COUNT(*) FROM recommendplan
    WHERE id = #{id} AND plan_id = #{planId}
    """)
    boolean exists(@Param("id") int id, @Param("planId") int planId);
}