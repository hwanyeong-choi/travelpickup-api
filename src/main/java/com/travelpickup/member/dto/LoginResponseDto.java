package com.travelpickup.member.dto;

import com.travelpickup.member.enums.LoginProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String token;

    private String type;

    @Builder
    private LoginResponseDto(String token,
                            String type) {
        this.token = token;
        this.type = type;
    }

    public static LoginResponseDto of(String token) {
        return LoginResponseDto
                .builder()
                .type("Bearer")
                .token(token)
                .build();
    }

}
