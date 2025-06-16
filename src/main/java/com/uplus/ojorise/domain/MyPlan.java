package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPlan {
    private String name;
    private String baseDataGb;
    private String dailyDataGb;
    private String sharingDataGb;
    private int monthlyFee;
    private String voiceCallPrice;
    private String sms;
    private int throttleSpeedKbps;
    private String mobileType;
}