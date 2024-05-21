package com.travelpickup.member.dto;

import com.travelpickup.member.domain.TravelPickupUser;
import com.travelpickup.member.enums.LoginProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "유저 정보 Dto")
public class TravelPickupUserMeResponseDto {

    @Schema(description = "유저 ID")
    private Long id;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "소셜 로그인 Provider")
    private LoginProvider loginProvider;

    @Builder
    public TravelPickupUserMeResponseDto(Long id,
                                         String nickName,
                                         LoginProvider loginProvider) {
        this.id = id;
        this.nickName = nickName;
        this.loginProvider = loginProvider;
    }

    public static TravelPickupUserMeResponseDto of(TravelPickupUser travelPickupUser) {
        return TravelPickupUserMeResponseDto
                .builder()
                .id(travelPickupUser.getId())
                .nickName(travelPickupUser.getNickName())
                .loginProvider(travelPickupUser.getProvider())
                .build();
    }

}
