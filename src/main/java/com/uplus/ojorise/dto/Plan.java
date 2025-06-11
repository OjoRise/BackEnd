package com.uplus.ojorise.dto;

import lombok.Data;

@Data
public class Plan {
    private int planId;
    private String name;
    private Integer baseDataGb;
    private Integer dailyDataGb;
    private Integer dailyDataMb;
    private Integer sharingDataGb;
    private Integer sharingDataMb;
    private Integer monthlyFee;
    private Boolean smsIncluded;
    private Integer voiceCallPrice;
    private Integer throttleSpeedKbps;

    private String eligibility;
    private String mobileType;
    private String dataType;
    private String planUrl;
    private String telecomProvider;
}
