package com.travelpickup.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelpickup.member.dto.TravelPickupUserMeResponseDto;
import com.travelpickup.member.service.TravelPickupUserService;
import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TravelPickup User Info Api")
@RestController
@RequestMapping("/api/v1/me")
public class TravelPickupUserController {

	private final TravelPickupUserService travelPickupUserService;

	public TravelPickupUserController(TravelPickupUserService travelPickupUserService) {
		this.travelPickupUserService = travelPickupUserService;
	}

	@GetMapping
	@Operation(summary = "유저정보 Api", description = "TravelPickup User Info Api")
	@ApiResponse(responseCode = "200", description = "유저 정보", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TravelPickupUserMeResponseDto.class)))
	public ResponseEntity<TravelPickupUserMeResponseDto> getTravelPickupUserInfo(@CurrentUser LoginUser loginUser) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(travelPickupUserService.getTravelPickupUserInfo(loginUser.getId()));
	}

}
