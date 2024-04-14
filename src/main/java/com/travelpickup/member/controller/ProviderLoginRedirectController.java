package com.travelpickup.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
public class ProviderLoginRedirectController {

    private final String REDIRECT_URI = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=73b5d35c4547d14470278fb2eb47f6b0&redirect_uri=http://localhost:5173/kakao";

    @GetMapping
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello TravelPickup Application Server");
    }

    @GetMapping("/kakao")
    public ResponseEntity<Void> kakaoLoginRedirectUri() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(REDIRECT_URI))
                .build();
    }

}
