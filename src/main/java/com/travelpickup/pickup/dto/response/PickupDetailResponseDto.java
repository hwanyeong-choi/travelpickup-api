package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PickupDetailResponseDto {

    private PickupResponseDto pickup;

    private DestinationLocationResponseDto destinationLocation;

    private List<PickupProductResponseDto> productList;

    private String qrUrl;

    @Builder
    public PickupDetailResponseDto(PickupResponseDto pickup,
                                   DestinationLocationResponseDto destinationLocation,
                                   List<PickupProductResponseDto> productList,
                                   String qrUrl) {
        this.pickup = pickup;
        this.destinationLocation = destinationLocation;
        this.productList = productList;
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
                .productList(productResponseList)
                .qrUrl(qrUrl)
                .build();
    }

}
