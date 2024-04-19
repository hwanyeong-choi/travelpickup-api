package com.travelpickup.common;

import lombok.Builder;
import lombok.Getter;

//@Getter
public class ResponseDto<T> {

    private String message;
    private int code;
    private T data;

    @Builder
    public ResponseDto(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseDto createOk(T data) {
        return ResponseDto
                .builder()
                .message("성공")
                .code(200)
                .data(data)
                .build();
    }

    public static <T> ResponseDto createOkNodata(String message) {
        return ResponseDto
                .builder()
                .message(message)
                .code(200)
                .data(null)
                .build();
    }

    public static ResponseDto createBadRequest(String message) {
        return ResponseDto
                .builder()
                .message(message)
                .code(400)
                .build();
    }

    public static ResponseDto createInternalServerError(String message) {
        return ResponseDto
                .builder()
                .message(message)
                .code(500)
                .build();
    }



}
