package com.uplus.ojorise.mapper;

import com.uplus.ojorise.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByKakaoId(String kakaoId);
    User findByUserId(Long userId);
    void insertUser(User user);
    void deleteByUserId(Long userId);
    void markSurvey(Long userId);
    void markWithdraw(Long userId);
    void cancelWithdraw(Long userId);
}
