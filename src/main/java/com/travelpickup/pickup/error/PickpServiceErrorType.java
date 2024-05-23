package com.travelpickup.pickup.error;

import com.travelpickup.common.error.TravelPickupErrorType;

public enum PickpServiceErrorType implements TravelPickupErrorType {

	PICKUP_ALREADY_IN_PROGRESS("이미 진행중인 픽업이 존재합니다."), INVALID_PICKUP_ID("잘못된 픽업 ID 입니다."), CANNOT_CANCEL_PICKUP(
		"취소가 불가능한 픽업 입니다.");

	private String message;

	PickpServiceErrorType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}


