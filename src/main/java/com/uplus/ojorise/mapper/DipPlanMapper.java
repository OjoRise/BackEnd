package com.uplus.ojorise.mapper;

import com.uplus.ojorise.dto.Plan;
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

    @Delete("DELETE FROM dipplan WHERE id = #{id} AND plan_id = #{planId}")
    void delete(@Param("id") int id, @Param("planId") int planId);
}
