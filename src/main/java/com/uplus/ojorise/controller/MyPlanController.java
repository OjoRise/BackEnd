package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.MyPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.uplus.ojorise.service.MyPlanService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPlanController {
    @Autowired
    private MyPlanService myPlanService;

    @GetMapping("/myplan")
    public List<MyPlan> getMyPlanByName (
            @RequestParam(required = false)
            String name
    ) {
        if (name == null) {
            return myPlanService.findMyPlanAll();
        } else {
            return myPlanService.findMyPlanByName(name);
        }
    }
}