package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.BrowsePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrowsePlanMapper {
    List<BrowsePlan> getBrowsePlanByIsOnline(
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("isOnline") boolean isOnline
    );

    List<BrowsePlan> getBrowsePlanWithDipByIsOnline(
            @Param("userId") Long userId,
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("isOnline") boolean isOnline
    );
}

