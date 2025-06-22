package com.uplus.ojorise.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

enum Eligibility {
    ALL, KID, BOY, YOUTH, OLD, SOLDIER
}

enum TelecomProvider {
    SKT, LG, KT
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    private Long planId;
    private String name;
    private String baseDataGb;
    private String dailyDataGb;
    private String sharingDataGb;
    private int monthlyFee;
    private String voiceCallPrice;
    private String sms;
    private int throttleSpeedKbps;
    private Eligibility eligibility;
    private String mobileType;
    private String planUrl;
    private String telecomProvider;
    private String description;
    private String benefit;
    @JsonProperty("isOnline")
    private boolean isOnline;
}
