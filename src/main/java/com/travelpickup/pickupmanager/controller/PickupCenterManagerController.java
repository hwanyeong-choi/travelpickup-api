package com.travelpickup.pickupmanager.controller;

import com.travelpickup.member.dto.CurrentUser;
import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.pickupmanager.dto.PickupCenterRegisterRequestDto;
import com.travelpickup.pickupmanager.service.PickupCenterManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager")
public class PickupCenterManagerController {

    private final PickupCenterManagerService pickupCenterManagerService;

    public PickupCenterManagerController(PickupCenterManagerService pickupCenterManagerService) {
        this.pickupCenterManagerService = pickupCenterManagerService;
    }

    @PostMapping("/center")
    public ResponseEntity<String> registerPickupCenter(@CurrentUser LoginUser loginUser,
                                                       @RequestBody PickupCenterRegisterRequestDto pickupCenterRegisterRequestDto) {
        pickupCenterManagerService.registerPickupCenter(pickupCenterRegisterRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
