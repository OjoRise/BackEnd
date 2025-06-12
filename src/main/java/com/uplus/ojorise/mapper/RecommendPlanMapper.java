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
    JOIN plan p ON r.plan_name = p.name
    WHERE r.id = #{id}
    """)
    List<Plan> findByUserId(int id);

    @Insert("""
    INSERT INTO recommendplan (plan_name, id)
    VALUES (#{planName}, #{id})
    """)
    void insert(RecommendPlan recommendPlan);

    @Delete("""
    DELETE FROM recommendplan
    WHERE id = #{id} AND plan_name = #{planName}
    """)
    void delete(@Param("id") int id, @Param("planName") String planName);

    @Select("""
    SELECT COUNT(*) > 0 FROM recommendplan
    WHERE id = #{id} AND plan_name = #{planName}
    """)
    boolean exists(@Param("id") int id, @Param("planName") String planName);
}