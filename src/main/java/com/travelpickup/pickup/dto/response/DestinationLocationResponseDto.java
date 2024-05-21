package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.PickupCenter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "숙소 Dto")
public class DestinationLocationResponseDto {

    @Schema(description = "숙소 주소")
    private String address;

    @Schema(description = "숙소 상세주소")
    private String addressDetail;

    @Schema(description = "숙소 위도")
    private Double latitude;

    @Schema(description = "숙소 경도")
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
