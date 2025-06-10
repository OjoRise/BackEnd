package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String kakaoId;
    private String nickname;
    private Boolean isSurveyed; //회원가입까지 완료했는가
    private Boolean isWithdraw; //탈퇴
}