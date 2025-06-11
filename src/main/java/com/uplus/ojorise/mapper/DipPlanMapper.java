package com.uplus.ojorise.mapper;

import com.uplus.ojorise.dto.DipPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DipPlanMapper {

    @Select("SELECT * FROM dipplan WHERE id = #{id}")
    List<DipPlan> findByUserId(int id);

    @Delete("DELETE FROM dipplan WHERE id = #{id} AND plan_id = #{planId}")
    void delete(@Param("id") int id, @Param("planId") int planId);
}
