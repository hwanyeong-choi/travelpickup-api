package com.travelpickup.pickup.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum PickupState {

    PICKUP_REQUEST_COMPLETED("픽업 신청완료"),
    PICKUP_CENTER_REQUEST_COMPLETED("픽업 센터 접수완료"),
    PICKUP_DELIVERY_AWAITING("픽업 배송대기중"),
    PICKUP_DELIVERY_IN_PROGRESS("픽업 배송중"),
    PICKUP_DELIVERY_COMPLETED("픽업 배송완료"),
    PICKUP_CANCEL("픽업 취소");


    private String description;

    PickupState(String description) {
        this.description = description;
    }

    public static List<PickupState> getAlreadyProgressStateList() {
        return Arrays.asList(
                PICKUP_REQUEST_COMPLETED,
                PICKUP_CENTER_REQUEST_COMPLETED,
                PICKUP_DELIVERY_AWAITING,
                PICKUP_DELIVERY_IN_PROGRESS,
                PICKUP_DELIVERY_COMPLETED);
    }

    public static List<PickupState> getInProgressStateList() {
        return Arrays.asList(
                PICKUP_CENTER_REQUEST_COMPLETED,
                PICKUP_DELIVERY_AWAITING,
                PICKUP_DELIVERY_IN_PROGRESS,
                PICKUP_DELIVERY_COMPLETED);
    }

    public static List<PickupState> getFinishStateList() {
        return Arrays.asList(
                PICKUP_CANCEL,
                PICKUP_DELIVERY_IN_PROGRESS
        );
    }

}