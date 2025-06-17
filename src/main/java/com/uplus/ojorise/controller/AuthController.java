package com.uplus.ojorise.controller;

import com.uplus.ojorise.dto.LoginResponse;
import com.uplus.ojorise.mapper.TokenMapper;
import com.uplus.ojorise.service.AuthService;
import com.uplus.ojorise.service.KakaoApiService;
import com.uplus.ojorise.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final KakaoApiService kakaoApiService;
    private final UserService userService;
    private final TokenMapper tokenMapper;

    //kakao
    @GetMapping("/kakao/login")
    public void redirectToKakao(HttpServletResponse response) throws IOException{
        String url = kakaoApiService.getKakaoLoginUrl();
        response.sendRedirect(url);
    }

    @GetMapping("/kakao/callback")
    public void kakaoCallback(@RequestParam String code, HttpServletResponse response) throws IOException {
        LoginResponse loginResponse = authService.loginWithKakao(code);

        // refreshToken cookie
        Cookie refreshCookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(60 * 60 * 24 * 14); // 14days
        refreshCookie.setDomain("localhost");

        response.addCookie(refreshCookie);

        //accessToken
        String redirectUrl = "http://localhost:3000/login/success?accessToken=" + loginResponse.getAccessToken();
        response.sendRedirect(redirectUrl);
    }

    //service
    @PostMapping("/refresh") //accessToken 재발급
    public ResponseEntity<?> refreshAccessToken(@CookieValue(name = "refreshToken", required = false)String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.badRequest().body("refreshToken 없음");
        }
        try {
            String newAccessToken = authService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("accessToken 재발급 실패");
        }
    }

    @PostMapping("/logout") //logout
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();
        String accessToken = (String) request.getAttribute("accessToken");

        tokenMapper.deleteByUserId(userId);

        Cookie expiredCookie = new Cookie("refreshToken", null);
        expiredCookie.setMaxAge(0);
        expiredCookie.setPath("/");
        expiredCookie.setDomain("localhost");
        expiredCookie.setHttpOnly(true);
        expiredCookie.setSecure(true);
        response.addCookie(expiredCookie);

        return ResponseEntity.ok("로그아웃 완료");
    }

    @DeleteMapping("/withdraw") //withdraw
    public ResponseEntity<?> withdraw( HttpServletRequest request, Authentication authentication) {
        try {
            Long userId = (Long) authentication.getPrincipal();
            String accessToken = (String) request.getAttribute("accessToken");

            String result = userService.withdrawUser(accessToken);
            return ResponseEntity.ok().body(Map.of("message", result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("message", "탈퇴 실패", "error", e.getMessage())
            );
        }
    }

    @Operation(summary = "설문 완료 처리", description = "user 테이블의 is_surveyed 값을 true로 업데이트")
    @PatchMapping("/survey/complete")
    public ResponseEntity<?> completeSurvey(Authentication authentication) {
        try {
            Long userId = (Long) authentication.getPrincipal();
            userService.completeSurvey(userId);
            return ResponseEntity.ok(Map.of("message", "설문조사 완료 처리됨"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("message", "설문조사 처리 실패", "error", e.getMessage())
            );
        }
    }
}
