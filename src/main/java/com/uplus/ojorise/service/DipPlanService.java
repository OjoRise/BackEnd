package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Plan;
import com.uplus.ojorise.mapper.DipPlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DipPlanService {

    private final DipPlanMapper dipPlanMapper;

    public List<Plan> getByUserId(int id) {
        return dipPlanMapper.findByUserId(id);
    }

    public void delete(int id, int planId) {
        dipPlanMapper.delete(id, planId);
    }

    public void insert(int id, int planId) { dipPlanMapper.insert(id, planId); }
}
