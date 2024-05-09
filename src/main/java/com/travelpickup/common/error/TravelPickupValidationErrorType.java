package com.travelpickup.common.error;

public enum TravelPickupValidationErrorType implements TravelPickupErrorType {

    TRAVEL_PICKUP_VALIDATION_ERROR("유효성 검증 에러");

    TravelPickupValidationErrorType(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

}
