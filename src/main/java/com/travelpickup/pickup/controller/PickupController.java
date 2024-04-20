package com.travelpickup.pickup.controller;

import com.travelpickup.member.dto.CurrentUser;
import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.pickup.dto.PickUpRegisterRequestDto;
import com.travelpickup.pickup.service.PickupService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pickups")
public class PickupController {

    private final PickupService pickupService;

    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;
    }

    @PostMapping
    public ResponseEntity<String> pickupRegister(@CurrentUser LoginUser loginUser,
                                                 @RequestPart(required = true) PickUpRegisterRequestDto pickUpRegisterRequestDto,
                                                 @RequestPart(required = false) List<MultipartFile> pickupProductsPhotoFiles) throws IOException {
        pickupService.pickupSave(pickUpRegisterRequestDto, pickupProductsPhotoFiles, loginUser.getId());
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("ok");
    }

}
