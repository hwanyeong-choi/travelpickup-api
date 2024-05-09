package com.travelpickup.pickup.controller;

import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginUser;
import com.travelpickup.pickup.dto.response.MyPickupResponseDto;
import com.travelpickup.pickup.dto.request.PickUpRegisterRequestDto;
import com.travelpickup.pickup.dto.response.PickupDetailResponseDto;
import com.travelpickup.pickup.service.PickupSearchService;
import com.travelpickup.pickup.service.PickupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pickups")
public class PickupController {

    private final PickupService pickupService;
    private final PickupSearchService pickupSearchService;

    public PickupController(PickupService pickupService,
                            PickupSearchService pickupSearchService) {
        this.pickupService = pickupService;
        this.pickupSearchService = pickupSearchService;
    }

    @PostMapping
    public ResponseEntity<String> registerPickup(@CurrentUser LoginUser loginUser,
                                                 @Valid
                                                 @RequestPart(required = true)
                                                 PickUpRegisterRequestDto pickUpRegisterRequestDto,
                                                 @NotNull(message = "값이 null일 수 없습니다.")
                                                 @NotEmpty(message = "값이 비어있을 수 없습니다.")
                                                 @RequestPart(required = true) List<MultipartFile> pickupProductsPhotoFiles) throws IOException {
        pickupService.pickupSave(pickUpRegisterRequestDto, pickupProductsPhotoFiles, loginUser.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("OK");
    }

    @GetMapping
    public ResponseEntity<MyPickupResponseDto> getMyPickups(@CurrentUser LoginUser loginUser) {
        MyPickupResponseDto myPickupResponseDto = pickupSearchService.getMyPickup(loginUser.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myPickupResponseDto);
    }

    @GetMapping("/{pickupId}")
    public ResponseEntity<PickupDetailResponseDto> getPickup(@CurrentUser LoginUser loginUser,
                                                             @PathVariable(name = "pickupId", required = true) Long pickupId) {
        PickupDetailResponseDto pickupDetailResponseDto = pickupSearchService.getPickup(loginUser.getId(), pickupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pickupDetailResponseDto);
    }

    @DeleteMapping("/{pickupId}")
    public ResponseEntity<String> cancelPickup(@CurrentUser LoginUser loginUser,
                                               @PathVariable(name = "pickupId", required = true) Long pickupId) {
        pickupService.getPickupCancelAble(loginUser.getId(), pickupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
