package com.travelpickup.common.dto;

import static com.travelpickup.common.error.TravelPickupValidationErrorType.*;

import com.travelpickup.common.error.TravelPickupErrorType;
import com.travelpickup.common.exception.TravelPickupServiceException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "TravelPickup Error Dto")
public class ErrorResponseDto {

	@Schema(description = "에러 타입")
	private TravelPickupErrorType errorType;

	@Schema(description = "Api 엔드포인트")
	private String path;

	@Schema(description = "에러 메세지")
	private String message;

	@Builder
	public ErrorResponseDto(TravelPickupErrorType errorType, String path, String message) {
		this.errorType = errorType;
		this.path = path;
		this.message = message;
	}

	public static ErrorResponseDto createTravelPickup(TravelPickupServiceException exception, String path) {
		return ErrorResponseDto.builder()
			.errorType(exception.getErrorType())
			.path(path)
			.message(exception.getMessage())
			.build();
	}

	public static ErrorResponseDto createInternalService(Exception exception, String path) {
		return ErrorResponseDto.builder().path(path).message(exception.getMessage()).build();
	}

	public static ErrorResponseDto createValidation(String message, String path) {
		return ErrorResponseDto.builder().errorType(TRAVEL_PICKUP_VALIDATION_ERROR).path(path).message(message).build();
	}

}
