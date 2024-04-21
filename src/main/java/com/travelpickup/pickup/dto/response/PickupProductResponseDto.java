package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.PickupProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PickupProductResponseDto {

    private String name;

    private Long quantity;

    @Builder
    public PickupProductResponseDto(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static PickupProductResponseDto of(PickupProduct pickupProduct) {
        return PickupProductResponseDto
                .builder()
                .name(pickupProduct.getName())
                .quantity(pickupProduct.getQuantity())
                .build();
    }

}
