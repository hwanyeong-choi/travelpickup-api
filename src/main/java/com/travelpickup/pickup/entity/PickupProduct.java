package com.travelpickup.pickup.entity;

import com.travelpickup.pickup.dto.PickupProductDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "pickup_product")
public class PickupProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickupProductId;

    @Column(name = "pickup_id")
    private Long pickupId;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;

    @Builder
    public PickupProduct(Long pickupId,
                         String name, Long quantity) {
        this.pickupId = pickupId;
        this.name = name;
        this.quantity = quantity;
    }

    public static PickupProduct of(PickupProductDto pickupProductDto,
                                   Long pickupId) {
        return PickupProduct
                .builder()
                .pickupId(pickupId)
                .name(pickupProductDto.getName())
                .quantity(pickupProductDto.getQuantity())
                .build();
    }

}
