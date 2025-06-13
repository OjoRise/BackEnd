package com.uplus.ojorise.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendedPlan {
    private Long id;
    private String planName;
}
