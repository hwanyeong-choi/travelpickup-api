package com.travelpickup.common.exception;

import com.travelpickup.common.error.TravelPickupErrorType;

import lombok.Getter;

@Getter
public class TravelPickupServiceException extends RuntimeException {

	private TravelPickupErrorType errorType;

	public TravelPickupServiceException(TravelPickupErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

}
