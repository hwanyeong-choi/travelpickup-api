package com.travelpickup.pickupmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupCenterRegisterRequestDto {

    private String name;

    private String address;

    private String addressDetail;

    private Double latitude;

    private Double longitude;

}
