package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.TongBTI;
import com.uplus.ojorise.dto.TongBTIPlanResponse;
import com.uplus.ojorise.mapper.TongBTIMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TongBTIService {
    private final TongBTIMapper tongBTIMapper;
    private final JwtUtil jwtUtil;

    public TongBTI getTongResult(String accessToken) {
        Long userId = jwtUtil.getUserIdFromToken(accessToken);
        return tongBTIMapper.getResult(userId);
    }

    public Long saveTongBTI(Long userId, String tongName) {
        Long tongId = tongBTIMapper.findTongIdByName(tongName);
        if (tongId == null) throw new RuntimeException("해당 유형 없음");

        tongBTIMapper.insertTongBTIResult(userId, tongId);
        return tongId;
    }

    public TongBTIPlanResponse getTongBTIWithPlanInfo(String tongName) {
        return tongBTIMapper.findTongBTIWithPlan(tongName);
    }
}
