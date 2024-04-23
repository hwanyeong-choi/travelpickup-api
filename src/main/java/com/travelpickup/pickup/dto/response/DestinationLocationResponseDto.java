package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.PickupCenter;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DestinationLocationResponseDto {

    private String description;

    private Double latitude;

    private Double longitude;

    @Builder
    public DestinationLocationResponseDto(String description,
                                          Double latitude,
                                          Double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static DestinationLocationResponseDto of(PickupCenter pickupCenter) {
        return DestinationLocationResponseDto
                .builder()
                .description(pickupCenter.getDescription())
                .latitude(pickupCenter.getLatitude())
                .longitude(pickupCenter.getLongitude())
                .build();
    }

    public static DestinationLocationResponseDto of(DestinationLocation destinationLocation) {
        return DestinationLocationResponseDto
                .builder()
                .description(destinationLocation.getDescription())
                .latitude(destinationLocation.getLatitude())
                .longitude(destinationLocation.getLongitude())
                .build();
    }

}
