package com.travelpickup.member.controller;

import com.travelpickup.member.dto.LoginResponseDto;
import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.service.KakaoOautService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private KakaoOautService kakaoLoginService;

    public LoginController(KakaoOautService kakaoLoginService) {
        this.kakaoLoginService = kakaoLoginService;
    }

    @PostMapping("/{provider}/{code}")
    public ResponseEntity<LoginResponseDto> login(
            @PathVariable(name = "provider", required = true) LoginProvider loginProvider,
            @PathVariable(name = "code", required = true) String code) {
        LoginResponseDto loginResponseDto = kakaoLoginService.login(code);
        return ResponseEntity.ok(loginResponseDto);
    }

}
