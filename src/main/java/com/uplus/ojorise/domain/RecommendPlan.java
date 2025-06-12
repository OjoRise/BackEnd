package com.uplus.ojorise.domain;

import lombok.Data;

@Data
public class RecommendPlan {
    private int id;              // 사용자 ID
    private String planName;     // 추천 요금제 이름
}
