package com.uplus.ojorise.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgeResult {
    private String age;
    private String description;
    private int recommend;
}
