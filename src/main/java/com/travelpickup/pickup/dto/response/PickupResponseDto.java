package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PickupResponseDto {

    private Long pickupId;

    private PickupState state;

    private String createAt;

    @Builder
    public PickupResponseDto(Long pickupId,
                             PickupState state,
                             String createAt) {
        this.pickupId = pickupId;
        this.state = state;
        this.createAt = createAt;
    }

    public static PickupResponseDto of(Pickup pickup) {
        return PickupResponseDto
                .builder()
                .pickupId(pickup.getPickupId())
                .state(pickup.getState())
                .createAt(pickup.getMyPickupFormatCreateAt())
                .build();
    }

}
