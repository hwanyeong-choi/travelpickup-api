package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PickupDetailResponseDto {

    private PickupResponseDto pickup;

    private PickupCenterResponseDto pickupCenterResponseDto;

    private DestinationLocationResponseDto destinationLocation;

    private List<PickupProductResponseDto> productResponseList;

    @Builder
    public PickupDetailResponseDto(PickupResponseDto pickup,
                                   PickupCenterResponseDto pickupCenterResponseDto,
                                   DestinationLocationResponseDto destinationLocation,
                                   List<PickupProductResponseDto> productResponseList) {
        this.pickup = pickup;
        this.pickupCenterResponseDto = pickupCenterResponseDto;
        this.destinationLocation = destinationLocation;
        this.productResponseList = productResponseList;
    }

    public static PickupDetailResponseDto of(PickupResponseDto pickup,
                                       PickupCenterResponseDto pickupLocation,
                                       DestinationLocationResponseDto descriptionLocation,
                                       List<PickupProductResponseDto> productResponseList) {
        return PickupDetailResponseDto
                .builder()
                .pickup(pickup)
                .pickupCenterResponseDto(pickupLocation)
                .destinationLocation(descriptionLocation)
                .productResponseList(productResponseList)
                .build();
    }

}
