package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Survey;
import com.uplus.ojorise.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SurveyMapper {
    void insertSurvey(Survey survey);
    List<Plan> findByTelecomProvider(@Param("telecomProvider") String telecomProvider);
    Survey getSurvey(@Param("id") Long id);
    void updateSurvey(Survey survey);
}
