package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/plan")
    public List<Plan> getPlanByProps(
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,
            @RequestParam(required = false) String telecomProvider,
            @RequestParam(required = false) String name
    ) {
        List<String> eligibilityList = new ArrayList<>();
        List<String> telecomProviderList = new ArrayList<>();
        eligibilityList.add("ALL");

        if (telecomProvider != null) {
            telecomProviderList.add(telecomProvider);
        } else {
            telecomProviderList.addAll(List.of("LG", "SKT", "KT"));
        }

        if (birthdate != null) {
            LocalDate birthLocalDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthLocalDate, LocalDate.now()).getYears();

            if (age <= 18) eligibilityList.add("청소년");
            else if (age <= 34) eligibilityList.add("청년");
            else if (age >= 65) eligibilityList.add("시니어");
        } else {
            eligibilityList.add("청소년");
            eligibilityList.add("청년");
            eligibilityList.add("시니어");
        }

        if (name != null && !name.isBlank()) {
            return planService.findPlanByPropsWithName(eligibilityList, telecomProviderList, name);
        } else {
            return planService.findPlanByPropsWithoutName(eligibilityList, telecomProviderList);
        }
    }
}

