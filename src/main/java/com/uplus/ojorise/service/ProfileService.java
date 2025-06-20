package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.Profile;
import com.uplus.ojorise.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileMapper profileMapper;

    public Profile getProfile(int id) {
        return profileMapper.getProfile(id);
    }
}
