package com.travelpickup.pickupmanager.controller;

import com.travelpickup.member.dto.CurrentUser;
import com.travelpickup.member.dto.LoginManager;
import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.pickupmanager.service.PickupManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pickup/manager")
public class PickupManagerController {

    public PickupManagerController(PickupManagerService pickupManagerService) {
        this.pickupManagerService = pickupManagerService;
    }

    private final PickupManagerService pickupManagerService;

    @PatchMapping("/{pickupId}")
    public ResponseEntity<String> enrolPickupByPickupCenter(@CurrentUser LoginManager loginManager,
                                                            @PathVariable(name = "pickupId", required = true) Long pickupId) {
        pickupManagerService.assignedCenterByPickup(loginManager, pickupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
