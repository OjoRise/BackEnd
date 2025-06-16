package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendPlan {
    private Long id;
    private String [] planName;
}
