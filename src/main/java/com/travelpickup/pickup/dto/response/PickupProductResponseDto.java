package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.PickupProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "픽업 물품 Dto")
public class PickupProductResponseDto {

    @Schema(description = "물품명")
    private String name;

    @Schema(description = "물품 개수")
    private Long quantity;

    @Schema(description = "물품 사진정보 base64")
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
