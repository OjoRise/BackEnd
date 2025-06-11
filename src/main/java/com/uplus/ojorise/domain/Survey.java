package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {
    private Long surveyId;
    private Long id;
    private String birthdate;
    private String telecomProvider;
    private String planName;
    private String contractTerm;
    private String familyBundle;
}
