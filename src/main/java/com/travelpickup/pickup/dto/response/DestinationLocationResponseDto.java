package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.PickupCenter;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DestinationLocationResponseDto {

    private String address;

    private String addressDetail;

    private Double latitude;

    private Double longitude;

    @Builder
    public DestinationLocationResponseDto(String address,
                                          String addressDetail,
                                          Double latitude,
                                          Double longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static DestinationLocationResponseDto of(PickupCenter pickupCenter) {
        return DestinationLocationResponseDto
                .builder()
                .address(pickupCenter.getAddress())
                .latitude(pickupCenter.getLatitude())
                .longitude(pickupCenter.getLongitude())
                .build();
    }

    public static DestinationLocationResponseDto of(DestinationLocation destinationLocation) {
        return DestinationLocationResponseDto
                .builder()
                .address(destinationLocation.getAddress())
                .addressDetail(destinationLocation.getAddressDetail())
                .latitude(destinationLocation.getLatitude())
                .longitude(destinationLocation.getLongitude())
                .build();
    }

}
