package com.uplus.ojorise.domain;

import lombok.*;

enum Eligibility {
    ALL, KID, BOY, YOUTH, OLD, SOLDIER, WELFARE
}

enum MobileType {
    _5G, LTE;

    @Override
    public String toString() {
        return this == _5G ? "5G" : name();
    }
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
    private Integer planId;
    private String name;
    private String baseDataGb;
    private String dailyDataGb;
    private String sharingDataGb;
    private Integer monthlyFee;
    private String voiceCallPrice;
    private String sms;
    private Integer throttleSpeedKbps;
    private Eligibility eligibility;
    private String mobileType;
    private Boolean isOnline;
    private String planUrl;
    private TelecomProvider telecomProvider;
    private String description;
}
