package com.travelpickup.pickup.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup_product_img")
public class PickupProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pickup_product_img_id", nullable = false)
    private Long pickupProductImgId;

    @Column(name = "pickup_product_id", nullable = false)
    private Long pickupProductId;

    @Column(name = "path", nullable = false)
    private String path;

    @Builder
    public PickupProductImg(Long pickupProductId,
                            String path) {
        this.pickupProductId = pickupProductId;
        this.path = path;
    }

    public static PickupProductImg of(String path) {
        return PickupProductImg
                .builder()
                .path(path)
                .build();
    }

    public void updatePickupProductId(Long pickupProductId) {
        this.pickupProductId = pickupProductId;
    }

}
