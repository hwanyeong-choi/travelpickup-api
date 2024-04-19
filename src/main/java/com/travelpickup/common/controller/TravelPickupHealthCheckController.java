package com.travelpickup.common.controller;

import com.travelpickup.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class TravelPickupHealthCheckController {

    @GetMapping
    public ResponseEntity<?> healthCheck() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.createOkNodata("TravelPickup Health Check"));

    }

}
