package com.uplus.ojorise.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanAgeResponse {
    private String age;
    private String result;
}
