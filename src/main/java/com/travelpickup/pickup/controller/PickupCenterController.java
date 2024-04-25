package com.travelpickup.pickup.controller;

import com.travelpickup.pickup.dto.response.PickupCenterResponseDto;
import com.travelpickup.pickup.service.PickupCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/centers")
public class PickupCenterController {

    private final PickupCenterService pickupCenterService;

    public PickupCenterController(PickupCenterService pickupCenterService) {
        this.pickupCenterService = pickupCenterService;
    }

    @GetMapping
    public ResponseEntity<List<PickupCenterResponseDto>> getPickupCenterList() {

        List<PickupCenterResponseDto> pickupCenterResponseDtoList = pickupCenterService.getPickupCenterList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pickupCenterResponseDtoList);

    }

    @GetMapping("/{pickupCenterId}")
    public ResponseEntity<PickupCenterResponseDto> getPickupCenter(@PathVariable(name = "pickupCenterId") Long pickupCenterId) {
        PickupCenterResponseDto pickupCenterResponseDto = pickupCenterService.getPickupCenterById(pickupCenterId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pickupCenterResponseDto);

    }

}
