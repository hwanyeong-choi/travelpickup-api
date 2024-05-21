package com.travelpickup.pickupmanager.controller;

import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginUser;
import com.travelpickup.pickupmanager.dto.PickupCenterRegisterRequestDto;
import com.travelpickup.pickupmanager.service.PickupCenterManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PickupCenterManager Api")
@RestController
@RequestMapping("/api/v1/manager")
public class PickupCenterManagerController {

    private final PickupCenterManagerService pickupCenterManagerService;

    public PickupCenterManagerController(PickupCenterManagerService pickupCenterManagerService) {
        this.pickupCenterManagerService = pickupCenterManagerService;
    }

    @Operation(summary = "픽업센터 등록 Api", description = "픽업센터 등록")
    @ApiResponse(responseCode = "200", description = "픽업센터 등록 완료", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "OK")))
    @PostMapping("/center")
    public ResponseEntity<String> registerPickupCenter(@CurrentUser LoginUser loginUser,
                                                       @Valid @RequestBody PickupCenterRegisterRequestDto pickupCenterRegisterRequestDto) {
        pickupCenterManagerService.registerPickupCenter(pickupCenterRegisterRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
