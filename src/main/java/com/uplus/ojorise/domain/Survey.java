package com.uplus.ojorise.domain;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {
    private Long surveyId;
    private Long id;
    private LocalDate birthdate;
    private String telecomProvider;
    private String planName;
    private Integer planPrice;
    private String familyBundle;
    private String familyNum;
}
