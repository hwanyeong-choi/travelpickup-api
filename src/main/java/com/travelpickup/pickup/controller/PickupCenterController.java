package com.travelpickup.pickup.controller;

import com.travelpickup.pickup.dto.response.PickupCenterResponseDto;
import com.travelpickup.pickup.service.PickupCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PickupCenter Info Api")
@RestController
@RequestMapping("/api/v1/pickup-centers")
public class PickupCenterController {

    private final PickupCenterService pickupCenterService;

    public PickupCenterController(PickupCenterService pickupCenterService) {
        this.pickupCenterService = pickupCenterService;
    }

    @Operation(summary = "픽업센터 정보 리스트 조회 Api", description = "픽업센터 정보 리스트 조회 Api")
    @ApiResponse(responseCode = "200",
            description = "픽업센터 정보 리스트",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = "array", implementation = PickupCenterResponseDto.class)))
    @GetMapping
    public ResponseEntity<List<PickupCenterResponseDto>> getPickupCenterList() {
        List<PickupCenterResponseDto> pickupCenterResponseDtoList = pickupCenterService.getPickupCenterList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pickupCenterResponseDtoList);
    }

    @Operation(summary = "픽업센터 정보 단건 조회 Api", description = "픽업센터 정보 단건 조회 Api")
    @GetMapping("/{pickupCenterId}")
    public ResponseEntity<PickupCenterResponseDto> getPickupCenter(
            @Parameter(description = "픽업센터 아이디") @PathVariable(name = "pickupCenterId") Long pickupCenterId) {
        PickupCenterResponseDto pickupCenterResponseDto = pickupCenterService.getPickupCenterById(pickupCenterId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pickupCenterResponseDto);
    }

}
