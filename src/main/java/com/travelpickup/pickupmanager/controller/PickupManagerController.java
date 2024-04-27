package com.travelpickup.pickupmanager.controller;

import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginManager;
import com.travelpickup.pickupmanager.service.PickupManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager/pickup")
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
