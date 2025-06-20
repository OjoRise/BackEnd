package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrowsePlan {
    private int planId;
    private String name;
    private String baseDataGb;
    private String dailyDataGb;
    private String sharingDataGb;
    private int monthlyFee;
    private String voiceCallPrice;
    private String sms;
    private String mobileType;
    private boolean isOnline;
    private String planUrl;
}
