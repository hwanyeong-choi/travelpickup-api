package com.travelpickup.pickup.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupLocationDto {

    private String description;

    private Double latitude;

    private Double longitude;

    public PickupLocationDto(String description,
                             Double latitude,
                             Double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
