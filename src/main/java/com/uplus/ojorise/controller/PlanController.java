package com.uplus.ojorise.controller;

import com.uplus.ojorise.client.PythonClient;
import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final PythonClient pythonClient;

    @GetMapping("/plan")
    public List<Plan> getPlanByBirthDate(
            @RequestParam(name = "birthdate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate
    ) {
        List<String> eligibilityList = new ArrayList<>();
        eligibilityList.add("ALL");

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


        return planService.findPlanByBirthDate(eligibilityList);
    }
    
    @PostMapping("/plan/sync")
    public ResponseEntity<Void> syncPlansToPython() {
        List<Plan> plans = planService.findAllPlansForLG();
        pythonClient.sendPlans(plans);
        return ResponseEntity.ok().build();
    }

}

