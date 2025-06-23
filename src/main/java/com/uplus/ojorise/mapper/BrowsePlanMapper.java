package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.BrowsePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrowsePlanMapper {
    List<BrowsePlan> getBrowsePlanByIsOnline(
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("isOnline") boolean isOnline
    );

    BrowsePlan getBrowsePlanById(@Param("id") int id);

    List<Integer> getBrowsePlanWithDipByIsOnline(
            @Param("userId") Long userId,
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("isOnline") boolean isOnline
    );
}

