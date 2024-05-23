package com.travelpickup.secutiry.enums;

import com.travelpickup.common.error.TravelPickupErrorType;

public enum JWTErrorType implements TravelPickupErrorType {

	JWT_TOKEN_IS_INVALID("JWT 토큰정보가 유효하지 않습니다.");

	private String message;

	JWTErrorType(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
