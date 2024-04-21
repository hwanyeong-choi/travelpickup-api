package com.travelpickup.pickup.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PickUpRegisterRequestDto {

    private PickupLocationDto pickupLocation;

    private PickupLocationDto descriptionLocation;

    private List<PickupProductDto> pickupProductDtoList;

}
