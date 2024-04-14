package com.travelpickup.pickup.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class PickUpRegisterRequestDto {

    private PlaceLocationDto pickupLocation;

    private PlaceLocationDto descriptionLocation;

    private List<PickupProductDto> pickupProductDtoList;

}
