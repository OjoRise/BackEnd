package com.uplus.ojorise.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TokenMapper {
    String findRefreshTokenByUserId(@Param("userId") Long userId);

    void insertRefreshToken(
            @Param("userId") Long userId,
            @Param("refreshToken") String refreshToken
    );

    void deleteByUserId(@Param("userId") Long userId);
}
