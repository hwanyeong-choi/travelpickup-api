package com.travelpickup.member.dto;

import com.travelpickup.member.enums.LoginProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "소셜 로그인 JWT 토큰 발생 DTO")
public class LoginResponseDto {

    @Schema(description = "JWT 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "토큰 타입", example = "Bearer")
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
