package com.travelpickup.pickupmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pickup/manager")
public class PickupManagerController {

    @PatchMapping("pickups/{pickupId}")
    public ResponseEntity<String> enrolPickupByPickupCenter(@PathVariable(name = "pickupId", required = true) Long pickupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
