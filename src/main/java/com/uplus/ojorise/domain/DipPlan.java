package com.uplus.ojorise.domain;

import lombok.Data;

@Data
public class DipPlan {
    private int dipId;     // AUTO_INCREMENT PK
    private int id;        // 사용자 ID (user.id)
    private int planId;    // 요금제 ID (plan.plan_id)
}
