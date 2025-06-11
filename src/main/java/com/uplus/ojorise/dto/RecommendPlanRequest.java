package com.uplus.ojorise.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecommendPlanRequest {
    private List<Integer> planIds;
}
