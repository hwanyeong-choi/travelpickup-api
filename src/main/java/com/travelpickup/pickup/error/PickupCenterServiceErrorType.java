package com.travelpickup.pickup.error;

import com.travelpickup.common.error.TravelPickupErrorType;

public enum PickupCenterServiceErrorType implements TravelPickupErrorType {

    INVALID_PICKUP_CENTER_ID("잘못된 픽업 ID 입니다.");

    private String message;

    PickupCenterServiceErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}