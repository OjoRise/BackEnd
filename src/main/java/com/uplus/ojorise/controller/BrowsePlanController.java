package com.uplus.ojorise.controller;

import com.uplus.ojorise.domain.BrowsePlan;
import com.uplus.ojorise.service.BrowsePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrowsePlanController {

    private final BrowsePlanService browsePlanService;

    @GetMapping("/browse")
    public ResponseEntity<List<BrowsePlan>> getBrowsePlanByIsOnline(
            Authentication authentication,
            @RequestParam(name = "isOnline") boolean isOnline,
            @RequestParam(name = "page") int page
    ) {
        int limit = 5;
        int offset = (page - 1) * limit;

        return ResponseEntity.ok(browsePlanService.getBrowsePlanByIsOnline(limit, offset, isOnline));
    }

    @GetMapping("/browseDip")
    public ResponseEntity<List<Integer>> getBrowseDipPlanByIsOnlineNonAuth(
            Authentication authentication,
            @RequestParam(name = "isOnline") boolean isOnline,
            @RequestParam(name = "page") int page
    ) {
        int limit = 5;
        int offset = (page - 1) * limit;

        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(browsePlanService.getBrowseDipPlanByIsOnlineNonAuth(userId, limit, offset, isOnline));
    }
}

