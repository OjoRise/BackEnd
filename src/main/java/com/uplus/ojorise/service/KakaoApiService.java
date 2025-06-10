package com.uplus.ojorise.service;

import com.uplus.ojorise.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final RestTemplate restTemplate;

    @Value("${kakao-api-key}")
    private String kakaoApiKey;

    @Value("${kakao-redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${kakao-client-secret}")
    private String kakaoClientSecret;

    public String getKakaoLoginUrl() {
        //redirect할 url
        return "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=" + kakaoApiKey +
                "&redirect_uri=" + kakaoRedirectUri +
                "&response_type=code";
    }

    public KakaoTokenResponse getToken(String code) {
        // access_token + refresh_token
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=authorization_code" +
                "&client_id=" + kakaoApiKey +
                "&redirect_uri=" + kakaoRedirectUri +
                "&code=" + code;

        if (!kakaoClientSecret.isBlank()) {
            body += "&client_secret=" + kakaoClientSecret;
        }

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null) {
            throw new RuntimeException("카카오 토큰 요청 실패");
        }

        KakaoTokenResponse token = new KakaoTokenResponse();
        token.setAccessToken((String) responseBody.get("access_token"));
        token.setRefreshToken((String) responseBody.get("refresh_token"));
        token.setExpiresIn((Integer) responseBody.get("expires_in"));
        token.setRefreshTokenExpiresIn((Integer) responseBody.get("refresh_token_expires_in"));
        return token;
    }

    public Map<String, Object> getUserInfo(String accessToken) {
        //user info
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Map.class
        );

        return response.getBody();
    }

    public KakaoTokenResponse refreshAccessToken(String refreshToken) {
        //refreshToken 으로 accessToken 갱신
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=refresh_token" +
                "&client_id=" + kakaoApiKey +
                "&refresh_token=" + refreshToken;

        if (!kakaoClientSecret.isBlank()) {
            body += "&client_secret=" + kakaoClientSecret;
        }

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    KakaoTokenResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("refreshToken 재발급 실패");
        }
    }

    public void kakaoWithdraw(String accessToken) {
        //withdraw
        String url = "https://kapi.kakao.com/v1/user/unlink";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String, Object> body = response.getBody();
        if (body == null || body.get("id") == null) {
            throw new RuntimeException("회원탈퇴 실패");
        }
    }
}
