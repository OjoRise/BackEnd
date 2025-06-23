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

    public List<BrowsePlan> getBrowsePlanByIsOnline(int limit, int offset, boolean isOnline) {
        return browsePlanMapper.getBrowsePlanByIsOnline(limit, offset, isOnline);
    }

    public List<Integer> getBrowseDipPlanByIsOnlineNonAuth(Long userId, int limit, int offset, boolean isOnline) {
        return browsePlanMapper.getBrowsePlanWithDipByIsOnline(userId, limit, offset, isOnline);
    }
}

