package com.uplus.ojorise.dto;

import lombok.Data;

@Data
public class KakaoTokenResponse {
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
    private int refreshTokenExpiresIn;
}
