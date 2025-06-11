package com.uplus.ojorise.domain;

import lombok.*;

enum Eligibility {
    ALL, 청소년, 청년, 시니어
}

enum DataType {
    무제한, 제한
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
    private int baseDataGb;
    private int dailyDataGb;
    private int dailyDataMb;
    private int sharingDataGb;
    private int sharingDataMb;
    private int monthlyFee;
    private int voiceCallPrice;
    private boolean smsIncluded;
    private int throttleSpeedKbps;
    private Eligibility eligibility;
    private String mobileType;
    private DataType dataType;
    private String planUrl;
    private TelecomProvider telecomProvider;
}
