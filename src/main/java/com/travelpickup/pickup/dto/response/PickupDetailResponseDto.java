package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PickupDetailResponseDto {

    private PickupResponseDto pickup;

    private DestinationLocationResponseDto destinationLocation;

    private List<PickupProductResponseDto> productResponseList;

    private String qrUrl;

    @Builder
    public PickupDetailResponseDto(PickupResponseDto pickup,
                                   DestinationLocationResponseDto destinationLocation,
                                   List<PickupProductResponseDto> productResponseList,
                                   String qrUrl) {
        this.pickup = pickup;
        this.destinationLocation = destinationLocation;
        this.productResponseList = productResponseList;
        this.qrUrl = qrUrl;
    }

    public static PickupDetailResponseDto of(PickupResponseDto pickup,
                                             DestinationLocationResponseDto descriptionLocation,
                                             List<PickupProductResponseDto> productResponseList,
                                             String qrUrl) {
        return PickupDetailResponseDto
                .builder()
                .pickup(pickup)
                .destinationLocation(descriptionLocation)
                .productResponseList(productResponseList)
                .qrUrl(qrUrl)
                .build();
    }

}
