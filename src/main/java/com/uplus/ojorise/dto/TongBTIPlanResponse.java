package com.uplus.ojorise.dto;

import lombok.Data;

@Data
public class TongBTIPlanResponse {
    private int tongId;
    private String tongName;
    private String tongDescription;

    private int planId;
    private String planName;
    private String baseDataGb;
    private String dailyDataGb;
    private String sharingDataGb;
    private int monthlyFee;
    private String voiceCallPrice;
    private String sms;
    private Integer throttleSpeedKbps;
    private String eligibility;
    private String mobileType;
    private boolean isOnline;
    private String planUrl;
    private String telecomProvider;
    private String planDescription;
}
