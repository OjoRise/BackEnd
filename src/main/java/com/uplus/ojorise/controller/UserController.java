package com.uplus.ojorise.controller;

import com.uplus.ojorise.client.PythonClient;
import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.domain.Profile;
import com.uplus.ojorise.service.PlanService;
import com.uplus.ojorise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Boolean> getUserIsSurvey(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserIsSurvey(userId));
    }
}

