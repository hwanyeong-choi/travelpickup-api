package com.travelpickup.member.dto;

import com.travelpickup.member.domain.TravelPickupUser;
import com.travelpickup.member.enums.LoginProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TravelPickupUserMeResponseDto {

    private Long id;
    private String nickName;
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
