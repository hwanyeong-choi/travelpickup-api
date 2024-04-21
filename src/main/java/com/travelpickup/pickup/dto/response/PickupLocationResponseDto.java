package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.PickupLocation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PickupLocationResponseDto {

    private String description;

    private Double latitude;

    private Double longitude;

    @Builder
    public PickupLocationResponseDto(String description,
                                     Double latitude,
                                     Double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static PickupLocationResponseDto of(PickupLocation pickupLocation) {
        return PickupLocationResponseDto
                .builder()
                .description(pickupLocation.getDescription())
                .latitude(pickupLocation.getLatitude())
                .longitude(pickupLocation.getLongitude())
                .build();
    }

    public static PickupLocationResponseDto of(DestinationLocation destinationLocation) {
        return PickupLocationResponseDto
                .builder()
                .description(destinationLocation.getDescription())
                .latitude(destinationLocation.getLatitude())
                .longitude(destinationLocation.getLongitude())
                .build();
    }

}
