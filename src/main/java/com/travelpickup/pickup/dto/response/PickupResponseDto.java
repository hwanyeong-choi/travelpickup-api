package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PickupResponseDto {

    private Long id;

    private Long centerId;

    private PickupState state;

    private String createAt;

    @Builder
    public PickupResponseDto(Long pickupId,
                             Long centerId,
                             PickupState state,
                             String createAt) {
        this.id = pickupId;
        this.centerId = centerId;
        this.state = state;
        this.createAt = createAt;
    }

    public static PickupResponseDto of(Pickup pickup) {
        return PickupResponseDto
                .builder()
                .pickupId(pickup.getPickupId())
                .centerId(pickup.getCenterId())
                .state(pickup.getState())
                .createAt(pickup.getMyPickupFormatCreateAt())
                .build();
    }

}
