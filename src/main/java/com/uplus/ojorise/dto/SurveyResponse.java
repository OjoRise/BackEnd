package com.uplus.ojorise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import lombok.Builder;

@Data
@Builder
public class SurveyResponse {
    private LocalDate birthdate;
    private String telecomProvider;
    private String planName;
    private Integer planPrice;
    private String familyBundle;
    private String familyNum;
}
