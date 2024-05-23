package com.travelpickup.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelpickup.member.dto.LoginResponseDto;
import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.service.KakaoOautService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TravelPickup Login Api")
@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

	private KakaoOautService kakaoLoginService;

	public LoginController(KakaoOautService kakaoLoginService) {
		this.kakaoLoginService = kakaoLoginService;
	}

	@Operation(summary = "소셜 로그인 Api", description = "소셜 로그인을 위한 Api")
	@PostMapping("/member/{provider}/{code}")
	public ResponseEntity<LoginResponseDto> login(
		@Parameter(description = "소셜 로그인 제공자") @PathVariable(name = "provider", required = true) LoginProvider loginProvider,
		@Parameter(description = "소셜 로그인 CODE") @PathVariable(name = "code", required = true) String code) {
		LoginResponseDto loginResponseDto = kakaoLoginService.login(code);
		return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
	}

	@Operation(summary = "어드민 소셜 로그인 Api", description = "어드민 소셜 로그인을 위한 Api")
	@PostMapping("/admin/{provider}/{code}")
	public ResponseEntity<LoginResponseDto> adminLogin(
		@Parameter(description = "소셜 로그인 제공자") @PathVariable(name = "provider", required = true) LoginProvider loginProvider,
		@Parameter(description = "소셜 로그인 CODE") @PathVariable(name = "code", required = true) String code) {
		LoginResponseDto loginResponseDto = kakaoLoginService.adminLogin(code);
		return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);

	}

}
