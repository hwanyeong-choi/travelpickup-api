package com.travelpickup.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.travelpickup.common.dto.ErrorResponseDto;

@RestControllerAdvice
public class TravelPickupGlobalExceptionHandler {

	@ExceptionHandler(TravelPickupServiceException.class)
	public ResponseEntity<?> handleTravelPickupServiceException(TravelPickupServiceException exception,
		WebRequest request) {

		exception.printStackTrace();
		String requestPath = request.getDescription(false).substring(4);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponseDto.createTravelPickup(exception, requestPath));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {

		exception.printStackTrace();
		String requestPath = request.getDescription(false).substring(4);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponseDto.createInternalService(exception, requestPath));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
		WebRequest request) {

		ex.printStackTrace();
		String requestPath = request.getDescription(false).substring(4);
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(String.format("(%s : %s)", error.getField(), error.getDefaultMessage()));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponseDto.createValidation(errors.toString(), requestPath));
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<?> handlerMethodValidationException(HandlerMethodValidationException ex, WebRequest request) {

		ex.printStackTrace();
		String requestPath = request.getDescription(false).substring(4);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponseDto.createValidation(ex.getMessage(), requestPath));
	}

}
