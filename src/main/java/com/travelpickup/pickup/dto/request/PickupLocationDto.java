package com.travelpickup.pickup.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupLocationDto {

    private String address;

    private String addressDetail;

    private Double latitude;

    private Double longitude;

    public PickupLocationDto(String address,
                             String addressDetail,
                             Double latitude,
                             Double longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
