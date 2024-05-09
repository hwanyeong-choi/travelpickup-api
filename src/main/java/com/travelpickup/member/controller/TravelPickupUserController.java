package com.travelpickup.member.controller;

import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginUser;
import com.travelpickup.member.service.TravelPickupUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/me")
public class TravelPickupUserController {

    private final TravelPickupUserService travelPickupUserService;

    public TravelPickupUserController(TravelPickupUserService travelPickupUserService) {
        this.travelPickupUserService = travelPickupUserService;
    }

    @GetMapping
    public ResponseEntity<?> getTravelPickupUserInfo(@CurrentUser LoginUser loginUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(travelPickupUserService.getTravelPickupUserInfo(loginUser.getId()));
    }

}
