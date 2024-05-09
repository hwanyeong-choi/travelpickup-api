package com.travelpickup.pickup.dto.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
public class PickUpRegisterRequestDto {

    @Valid
    private PickupLocationDto descriptionLocation;

    @Valid
    private List<PickupProductDto> pickupProductDtoList;

}
