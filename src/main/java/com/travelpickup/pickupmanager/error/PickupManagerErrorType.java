package com.travelpickup.pickupmanager.error;

import com.travelpickup.common.error.TravelPickupErrorType;
import lombok.Getter;

@Getter
public enum PickupManagerErrorType implements TravelPickupErrorType {

    NOT_FOUND_PICKUP("픽업을 목록에서 찾을 수 없습니다."),
    ASSIGNED_PICKUP("이미 배정된 픽업 입니다.");

    PickupManagerErrorType(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

}
