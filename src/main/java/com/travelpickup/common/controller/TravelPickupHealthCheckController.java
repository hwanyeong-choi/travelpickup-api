package com.travelpickup.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class TravelPickupHealthCheckController {

    private final String TRAVEL_PICKUP_HEALTH_CHECK_MESSAGE = "TravelPickup Health Check";

    @GetMapping
    public ResponseEntity<?> healthCheck() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(TRAVEL_PICKUP_HEALTH_CHECK_MESSAGE);

    }

}
