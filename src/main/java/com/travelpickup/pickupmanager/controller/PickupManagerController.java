package com.travelpickup.pickupmanager.controller;

import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginManager;
import com.travelpickup.pickupmanager.service.PickupManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PickupManager Api")
@RestController
@RequestMapping("/api/v1/manager/pickup")
public class PickupManagerController {

    public PickupManagerController(PickupManagerService pickupManagerService) {
        this.pickupManagerService = pickupManagerService;
    }

    private final PickupManagerService pickupManagerService;

    @Operation(summary = "픽업 픽업센터 접수 Api", description = "고객이 픽업센터에 픽업 물품을 접수할때 사용하는 Api")
    @ApiResponse(responseCode = "200", description = "픽업 픽업센터 접수 완료", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "OK")))
    @PatchMapping("/{pickupId}")
    public ResponseEntity<String> enrolPickupByPickupCenter(@CurrentUser LoginManager loginManager,
                                                            @Parameter(description = "접수하는 픽업 Id") @PathVariable(name = "pickupId", required = true) Long pickupId) {
        pickupManagerService.assignedCenterByPickup(loginManager, pickupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

}
