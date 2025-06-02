package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByKakaoId(String kakaoId);
    void insertUser(User user);
    void deleteByUserId(Long userId);
}
