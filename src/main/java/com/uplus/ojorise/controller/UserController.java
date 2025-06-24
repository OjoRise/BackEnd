package com.uplus.ojorise.controller;

import com.uplus.ojorise.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/name")
    @Operation(summary = "사용자의 이름 조회", description = "사용자의 이름을 불러옵니다.")
    public ResponseEntity<String> getUserName(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserName(userId));
    }

    @GetMapping("/user")
    @Operation(summary = "사용자의 회원가입 여부 조회", description = "사용자의 회원가입 여부를 불러옵니다.")
    public ResponseEntity<Boolean> getUserIsSurvey(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getUserIsSurvey(userId));
    }
}

