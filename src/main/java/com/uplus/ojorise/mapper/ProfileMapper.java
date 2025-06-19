package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfileMapper {
    Profile getProfile(
            @Param("id") int id
    );
}
