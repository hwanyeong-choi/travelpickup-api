package com.travelpickup.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TravelPickup Health Check api")
@RestController
@RequestMapping("/api/v1/health")
public class TravelPickupHealthCheckController {

    private final String TRAVEL_PICKUP_HEALTH_CHECK_MESSAGE = "TravelPickup Health Check";

    @GetMapping
    @Operation(summary = "헬스체크 api", description = "로드밸런스 헬스체크 Api")
    @ApiResponse(responseCode = "200",
            description = "TravelPickup Health Check",
            content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "TravelPickup Health Check")))
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(TRAVEL_PICKUP_HEALTH_CHECK_MESSAGE);
    }

}
