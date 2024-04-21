package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PickupDetailResponseDto {

    private PickupResponseDto pickup;

    private PickupLocationResponseDto pickupLocation;

    private PickupLocationResponseDto destinationLocation;

    private List<PickupProductResponseDto> productResponseList;

    @Builder
    public PickupDetailResponseDto(PickupResponseDto pickup,
                                   PickupLocationResponseDto pickupLocation,
                                   PickupLocationResponseDto destinationLocation,
                                   List<PickupProductResponseDto> productResponseList) {
        this.pickup = pickup;
        this.pickupLocation = pickupLocation;
        this.destinationLocation = destinationLocation;
        this.productResponseList = productResponseList;
    }

    public static PickupDetailResponseDto of(PickupResponseDto pickup,
                                       PickupLocationResponseDto pickupLocation,
                                       PickupLocationResponseDto descriptionLocation,
                                       List<PickupProductResponseDto> productResponseList) {
        return PickupDetailResponseDto
                .builder()
                .pickup(pickup)
                .pickupLocation(pickupLocation)
                .destinationLocation(descriptionLocation)
                .productResponseList(productResponseList)
                .build();
    }

}
