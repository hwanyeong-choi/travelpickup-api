package com.travelpickup.pickup.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travelpickup.common.dto.ErrorResponseDto;
import com.travelpickup.pickup.dto.request.PickUpRegisterRequestDto;
import com.travelpickup.pickup.dto.response.MyPickupResponseDto;
import com.travelpickup.pickup.dto.response.PickupDetailResponseDto;
import com.travelpickup.pickup.service.PickupSearchService;
import com.travelpickup.pickup.service.PickupService;
import com.travelpickup.secutiry.dto.CurrentUser;
import com.travelpickup.secutiry.dto.LoginUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Pickup Api")
@RestController
@RequestMapping("/api/v1/pickups")
public class PickupController {

	private final PickupService pickupService;
	private final PickupSearchService pickupSearchService;

	public PickupController(PickupService pickupService, PickupSearchService pickupSearchService) {
		this.pickupService = pickupService;
		this.pickupSearchService = pickupSearchService;
	}

	@Operation(summary = "픽업 신청 Api", description = "고객이 픽업신청을 하는 Apu")
	@ApiResponse(responseCode = "200", description = "픽업신청 완료", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "OK")))
	@ApiResponse(responseCode = "400", description = "픽업신청이 이미 진행중 입니다", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
	@PostMapping
	public ResponseEntity<String> registerPickup(@CurrentUser LoginUser loginUser,
		@Valid @RequestPart(required = true) PickUpRegisterRequestDto pickUpRegisterRequestDto,
		@NotNull(message = "값이 null일 수 없습니다.") @NotEmpty(message = "값이 비어있을 수 없습니다.") @Parameter(description = "업로드할 픽업 상품 사진 파일", content = @Content(mediaType = "multipart/form-data")) @RequestPart(required = true) List<MultipartFile> pickupProductsPhotoFiles) throws
		IOException {
		pickupService.pickupSave(pickUpRegisterRequestDto, pickupProductsPhotoFiles, loginUser.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body("OK");
	}

	@Operation(summary = "픽업 신청내역 조회 Api", description = "고객의 픽업신청 상태를 확인하는 Api")
	@ApiResponse(responseCode = "200", description = "픽업 조회 완료", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyPickupResponseDto.class)))
	@GetMapping
	public ResponseEntity<MyPickupResponseDto> getMyPickups(@CurrentUser LoginUser loginUser) {
		MyPickupResponseDto myPickupResponseDto = pickupSearchService.getMyPickup(loginUser.getId());
		return ResponseEntity.status(HttpStatus.OK).body(myPickupResponseDto);
	}

	@Operation(summary = "픽업 신청내역 단건 조회 Api", description = "특정 픽업신청 상태를 확인하는 Api")
	@ApiResponse(responseCode = "200", description = "픽업 조회 완료", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PickupDetailResponseDto.class)))
	@ApiResponse(responseCode = "400", description = "잘못된 픽업 ID 입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
	@GetMapping("/{pickupId}")
	public ResponseEntity<PickupDetailResponseDto> getPickup(@CurrentUser LoginUser loginUser,
		@Parameter(description = "픽업 Id") @PathVariable(name = "pickupId", required = true) Long pickupId) {
		PickupDetailResponseDto pickupDetailResponseDto = pickupSearchService.getPickup(loginUser.getId(), pickupId);
		return ResponseEntity.status(HttpStatus.OK).body(pickupDetailResponseDto);
	}

	@Operation(summary = "특정 픽업 신청을 취소하는 Api", description = "특정 픽업신청 취소하는 Api")
	@ApiResponse(responseCode = "200", description = "픽업 신청 취소 완료", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "OK")))
	@ApiResponse(responseCode = "400", description = "취소가 불가능한 픽업 입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
	@DeleteMapping("/{pickupId}")
	public ResponseEntity<String> cancelPickup(@CurrentUser LoginUser loginUser,
		@PathVariable(name = "pickupId", required = true) Long pickupId) {
		pickupService.getPickupCancelAble(loginUser.getId(), pickupId);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

}
