package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.User;
import com.uplus.ojorise.dto.KakaoTokenResponse;
import com.uplus.ojorise.mapper.TokenMapper;
import com.uplus.ojorise.mapper.UserMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final KakaoApiService kakaoApiService;
    private final UserMapper userMapper;
    private final TokenMapper tokenMapper;
    private final JwtUtil jwtUtil;

    public String withdrawUser(String accessToken){
        Long userId = jwtUtil.getUserIdFromToken(accessToken);

        String kakaoRefreshToken = tokenMapper.findRefreshTokenByUserId(userId);

        String kakaoUnlinkResult;

        if (kakaoRefreshToken != null) {
            try {
                KakaoTokenResponse newTokens = kakaoApiService.refreshAccessToken(kakaoRefreshToken);
                String kakaoAccessToken = newTokens.getAccessToken();
                if(kakaoAccessToken != null){
                    kakaoApiService.kakaoWithdraw(kakaoAccessToken);
                } else{
                    kakaoApiService.kakaoWithdraw(accessToken);
                }
                kakaoUnlinkResult = "카카오 연결 해제 완료";
            } catch (Exception e) {
                e.printStackTrace();
                kakaoUnlinkResult = "카카오 연결 해제 실패";
            }
        } else {
            kakaoUnlinkResult = "카카오 refreshToken 없음";
        }
        
        // DB 삭제
        tokenMapper.deleteByUserId(userId);
        // DB 변경
        userMapper.markWithdraw(userId);
        User user = userMapper.findByUserId(userId);
        user.setIsWithdraw(true);

        return kakaoUnlinkResult;
    }

    public void completeSurvey(Long userId) {
        userMapper.markSurvey(userId);
    }

    public boolean getUserIsSurvey(Long userId) { return userMapper.getUserIsSurvey(userId); };
}
