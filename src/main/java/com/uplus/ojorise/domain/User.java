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
    private boolean isSurveyed;
}