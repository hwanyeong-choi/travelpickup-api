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
    @Column(name = "pickup_product_img_id")
    private Long pickupProductImgId;

    @Column(name = "pickup_id")
    private Long pickupId;

    @Column(name = "path")
    private String path;

    @Builder
    public PickupProductImg(Long pickupId,
                            String path) {
        this.pickupId = pickupId;
        this.path = path;
    }

    public static PickupProductImg of(Long pickupId, String path) {
        return PickupProductImg
                .builder()
                .pickupId(pickupId)
                .path(path)
                .build();
    }



}
