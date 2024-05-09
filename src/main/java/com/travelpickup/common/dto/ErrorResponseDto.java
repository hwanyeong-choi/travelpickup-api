package com.travelpickup.common.dto;

import com.travelpickup.common.error.TravelPickupErrorType;
import com.travelpickup.common.exception.TravelPickupServiceException;
import lombok.Builder;
import lombok.Getter;

import static com.travelpickup.common.error.TravelPickupValidationErrorType.TRAVEL_PICKUP_VALIDATION_ERROR;

@Getter
public class ErrorResponseDto {

    private TravelPickupErrorType errorType;

    private String path;

    private String message;

    @Builder
    public ErrorResponseDto(TravelPickupErrorType errorType,
                            String path,
                            String message) {
        this.errorType = errorType;
        this.path = path;
        this.message = message;
    }

    public static ErrorResponseDto createTravelPickup(TravelPickupServiceException exception,
                                                      String path) {
        return ErrorResponseDto
                .builder()
                .errorType(exception.getErrorType())
                .path(path)
                .message(exception.getMessage())
                .build();
    }

    public static ErrorResponseDto createInternalService(Exception exception,
                                                         String path) {
        return ErrorResponseDto
                .builder()
                .path(path)
                .message(exception.getMessage())
                .build();
    }

    public static ErrorResponseDto createValidation(String message,
                                                    String path) {
        return ErrorResponseDto
                .builder()
                .errorType(TRAVEL_PICKUP_VALIDATION_ERROR)
                .path(path)
                .message(message)
                .build();
    }

}
