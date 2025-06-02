package com.uplus.ojorise.service;

import com.uplus.ojorise.domain.User;
import com.uplus.ojorise.dto.KakaoTokenResponse;
import com.uplus.ojorise.dto.LoginResponse;
import com.uplus.ojorise.mapper.TokenMapper;
import com.uplus.ojorise.mapper.UserMapper;
import com.uplus.ojorise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoApiService kakaoApiService;
    private final UserMapper userMapper;
    private final TokenMapper tokenMapper;
    private final JwtUtil jwtUtil;

    public LoginResponse loginWithKakao(String code){
        KakaoTokenResponse token = kakaoApiService.getToken(code);
        String accessToken = token.getAccessToken(); //kakao accessToken

        Map<String, Object> userInfo = kakaoApiService.getUserInfo(accessToken);

        // DB 저장
        String kakaoId = userInfo.get("id").toString();
        User user = userMapper.findByKakaoId(kakaoId);
        if(user == null){
            //join
            user = User.builder()
                    .kakaoId(kakaoId)
                    .build();
            userMapper.insertUser(user);
            user = userMapper.findByKakaoId(kakaoId); //Id 갱신
        }

        tokenMapper.insertRefreshToken(user.getId(), token.getRefreshToken());

        //JWT
        String jwtAccessToken =  jwtUtil.createAccessToken(user.getId());
        String jwtRefreshToken =  jwtUtil.createRefreshToken(user.getId());
        return new LoginResponse(jwtAccessToken, jwtRefreshToken);
    }


    public String refreshAccessToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 refreshToken");
        }

        Long userId = jwtUtil.getUserIdFromToken(refreshToken);

        return jwtUtil.createAccessToken(userId);
    }
}
