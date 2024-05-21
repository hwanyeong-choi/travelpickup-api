package com.travelpickup.pickup.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Schema(description = "픽업신청 Dto")
@Validated
public class PickUpRegisterRequestDto {

    @Valid
    @Schema(description = "숙소 위치")
    private PickupLocationDto descriptionLocation;

    @Valid
    @Schema(description = "픽업신청 물품")
    private List<PickupProductDto> pickupProductDtoList;

}
