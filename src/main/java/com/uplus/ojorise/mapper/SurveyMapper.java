package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Survey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper {
    void insertSurvey(Survey survey);
}
