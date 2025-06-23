package com.uplus.ojorise.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    private int id;
    private Date birthdate;
    private String telecomProvider;
    private String planName;
    private String familyBundle;
    private String tongName;
}
