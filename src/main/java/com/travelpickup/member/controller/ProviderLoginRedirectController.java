package com.travelpickup.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
public class ProviderLoginRedirectController {

    private final String kakaoLoginRedirectUri;

    public ProviderLoginRedirectController(@Value("${kakao.redirect-login-uri}") String kakaoLoginRedirectUri) {
        this.kakaoLoginRedirectUri = kakaoLoginRedirectUri;
    }

    @GetMapping
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello TravelPickup Application Server");
    }

    @GetMapping("/kakao")
    public ResponseEntity<Void> kakaoLoginRedirectUri() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(kakaoLoginRedirectUri))
                .build();
    }

}
