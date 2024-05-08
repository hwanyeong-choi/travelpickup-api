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

    private String viewState;

    private String createAt;

    @Builder
    public PickupResponseDto(Long pickupId,
                             Long centerId,
                             PickupState state,
                             String viewState,
                             String createAt) {
        this.id = pickupId;
        this.centerId = centerId;
        this.state = state;
        this.viewState = viewState;
        this.createAt = createAt;
    }

    public static PickupResponseDto of(Pickup pickup) {
        return PickupResponseDto
                .builder()
                .pickupId(pickup.getPickupId())
                .centerId(pickup.getCenterId())
                .state(pickup.getState())
                .viewState(pickup.getState().getViewState())
                .createAt(pickup.getMyPickupFormatCreateAt())
                .build();
    }

}
