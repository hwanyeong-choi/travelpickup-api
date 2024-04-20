package com.travelpickup.pickup.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum PickupState {

    PICKUP_PENDING("픽업 신청완료"),
    PICKUP_MAN_MATCHING("픽업맨 매칭중"),
    PICKUP_MAN_MATCHED("픽업맨 매칭 완료"),
    PICKUP_MAN_PICKUP_COMPLETE("픽업맨 픽업 완료"),
    PICKUP_MAN_DELIVERY_IN_PROGRESS("픽업맨 배송중"),
    PICKUP_MAN_DELIVERY_COMPLETED("픽업맨 배송 완료");


    private String description;

    PickupState(String description) {
        this.description = description;
    }

    public static List<PickupState> getInProgressStateList() {
        return Arrays.asList(
                PICKUP_MAN_MATCHING,
                PICKUP_MAN_MATCHED,
                PICKUP_MAN_PICKUP_COMPLETE,
                PICKUP_MAN_DELIVERY_IN_PROGRESS);
    }

}