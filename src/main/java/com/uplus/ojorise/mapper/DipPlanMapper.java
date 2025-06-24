package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DipPlanMapper {

    @Select("""
        SELECT p.*
        FROM dipplan d
        JOIN plan p ON d.plan_id = p.plan_id
        WHERE d.id = #{id}
    """)
    List<Plan> findByUserId(int id);

    @Select("""
        SELECT p.plan_id
        FROM dipplan d
        JOIN plan p ON d.plan_id = p.plan_id
        WHERE d.id = #{id}
    """)
    List<Integer> findByUserIdOnlyPlanId(int id);

    @Delete("DELETE FROM dipplan WHERE id = #{id} AND plan_id = #{planId}")
    void delete(@Param("id") int id, @Param("planId") int planId);

    @Insert("INSERT INTO dipplan(id, plan_id) VALUES (#{id}, #{planId})")
    void insert(@Param("id") int id, @Param("planId") int planId);
}
