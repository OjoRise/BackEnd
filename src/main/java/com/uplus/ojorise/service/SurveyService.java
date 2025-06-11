package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.Survey;
import com.uplus.ojorise.mapper.SurveyMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyMapper surveyMapper;
    private final JwtUtil jwtUtil;

    public void saveSurvey(String accessToken, Survey survey) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        survey.setId(userId);
        surveyMapper.insertSurvey(survey);
    }

    public List<Plan> getPlansByTelecomProvider(String telecomProvider) {
        return surveyMapper.findByTelecomProvider(telecomProvider);
    }
}
