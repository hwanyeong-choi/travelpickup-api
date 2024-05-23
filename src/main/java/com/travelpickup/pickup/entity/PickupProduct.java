package com.travelpickup.pickup.entity;

import com.travelpickup.pickup.dto.request.PickupProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup_product")
public class PickupProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pickupProductId;

	@Column(name = "pickup_id", nullable = false)
	private Long pickupId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "quantity", nullable = false)
	private Long quantity;

	@Builder
	public PickupProduct(Long pickupId, String name, Long quantity) {
		this.pickupId = pickupId;
		this.name = name;
		this.quantity = quantity;
	}

	public static PickupProduct of(PickupProductDto pickupProductDto, Long pickupId) {
		return PickupProduct.builder()
			.pickupId(pickupId)
			.name(pickupProductDto.getName())
			.quantity(pickupProductDto.getQuantity())
			.build();
	}

}
