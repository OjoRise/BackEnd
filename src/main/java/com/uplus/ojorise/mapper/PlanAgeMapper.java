package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.AgeResult;
import com.uplus.ojorise.domain.PlanAge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlanAgeMapper {
    @Select("""
    SELECT age AS age
    FROM planage
    WHERE id = #{id}
    LIMIT 1
    """)
    PlanAge getResult(@Param("id") Long id);

    @Select("""
    SELECT * FROM agetest Where age=#{age}
""")
    AgeResult getAgeResult(@Param("age") String age);

    @Insert("""
    INSERT INTO planage (id, age) 
    VALUES (#{id}, #{age})
    ON DUPLICATE KEY UPDATE
    age = #{age}
    """)
    void insertPlanAge(PlanAge planAge);
}