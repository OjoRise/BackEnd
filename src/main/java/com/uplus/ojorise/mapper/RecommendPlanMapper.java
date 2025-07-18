package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.RecommendPlan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecommendPlanMapper {

    List<Plan> findByUserId(int id);

    // 추천 요금제 저장
    @Insert("""
        INSERT INTO recommendplan (plan_id, id)
        VALUES (#{planId}, #{id})
    """)
    void insert(RecommendPlan recommendPlan);

    // 추천 요금제 삭제
    @Delete("""
        DELETE FROM recommendplan
        WHERE id = #{id} AND plan_id = #{planId}
    """)
    void delete(@Param("id") int id, @Param("planId") int planId);

    // 추천 요금제 존재 여부 확인
    @Select("""
        SELECT COUNT(*) > 0 FROM recommendplan
        WHERE id = #{id} AND plan_id = #{planId}
    """)
    boolean exists(@Param("id") int id, @Param("planId") int planId);

    @Select("SELECT plan_id FROM plan WHERE name = #{name}")
    Integer findPlanIdByName(@Param("name") String name);

    @Delete("""
DELETE FROM recommendplan
WHERE id = #{id}
  AND recommend_id IN (
    SELECT recommend_id FROM (
      SELECT recommend_id
      FROM recommendplan
      WHERE id = #{id}
      ORDER BY recommend_id desc
      LIMIT 999 OFFSET 5
    ) AS to_delete
  );
""")
    void maintain(@Param("id") int id);
}
