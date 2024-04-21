package com.travelpickup.common.dto;

import com.travelpickup.common.error.TravelPickupErrorType;
import com.travelpickup.common.exception.TravelPickupServiceException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponseDto {

    private TravelPickupErrorType errorType;

    private String message;

    @Builder
    public ErrorResponseDto(TravelPickupErrorType errorType,
                            String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public static ErrorResponseDto createTravelPickup(TravelPickupServiceException exception) {
        return ErrorResponseDto
                .builder()
                .errorType(exception.getErrorType())
                .message(exception.getMessage())
                .build();
    }

    public static ErrorResponseDto createInternalService(Exception exception) {
        return ErrorResponseDto
                .builder()
                .message(exception.getMessage())
                .build();
    }

}
