package com.travelpickup.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "TravelPickup Provider Login Redirect Api")
public class ProviderLoginRedirectController {

    private final String kakaoLoginRedirectUri;

    public ProviderLoginRedirectController(@Value("${kakao.redirect-login-uri}") String kakaoLoginRedirectUri) {
        this.kakaoLoginRedirectUri = kakaoLoginRedirectUri;
    }

    @Operation(summary = "Kakao 소셜 로그인 리다이렉트", description = "kakao 소셜 로그인 리다이렉트 Api")
    @ApiResponse(responseCode = "302",
            description = "Kakao Login Redirect",
            headers = @Header(name = "location", description = "Kakao Login Redirect Url", schema = @Schema(type = "string")))
    @GetMapping("/kakao")
    public ResponseEntity<Void> kakaoLoginRedirectUri() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(kakaoLoginRedirectUri))
                .build();
    }

}
