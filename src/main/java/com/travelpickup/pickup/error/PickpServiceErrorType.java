package com.travelpickup.pickup.error;


import com.travelpickup.common.error.TravelPickupErrorType;

public enum PickpServiceErrorType implements TravelPickupErrorType {

    PICKUP_ALREADY_IN_PROGRESS("이미 진행중인 픽업이 존재합니다.");

    private String message;

    PickpServiceErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}


