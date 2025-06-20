package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.BrowsePlan;
import com.uplus.ojorise.mapper.BrowsePlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowsePlanService {
    private final BrowsePlanMapper browsePlanMapper;

    public List<List<BrowsePlan>> getBrowsePlanByIsOnline(Long userId, int limit, int offset, boolean isOnline) {
        List<BrowsePlan> pagePlans = browsePlanMapper.getBrowsePlanByIsOnline(limit, offset, isOnline);
        List<BrowsePlan> dipPagePlans = browsePlanMapper.getBrowsePlanWithDipByIsOnline(userId, limit, offset, isOnline);

        return List.of(pagePlans, dipPagePlans);
    }
}

