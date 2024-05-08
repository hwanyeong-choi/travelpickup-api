package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.PickupProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PickupProductResponseDto {

    private String name;

    private Long quantity;

    private String productImgByBase64;

    @Builder
    public PickupProductResponseDto(String name,
                                    Long quantity,
                                    String productImgByBase64) {
        this.name = name;
        this.quantity = quantity;
        this.productImgByBase64 = productImgByBase64;
    }

    public static PickupProductResponseDto of(PickupProduct pickupProduct, String productImgByBase64) {
        return PickupProductResponseDto
                .builder()
                .name(pickupProduct.getName())
                .quantity(pickupProduct.getQuantity())
                .productImgByBase64(productImgByBase64)
                .build();
    }

    public static PickupProductResponseDto of(PickupProduct pickupProduct) {
        return PickupProductResponseDto
                .builder()
                .name(pickupProduct.getName())
                .quantity(pickupProduct.getQuantity())
                .build();
    }

}
