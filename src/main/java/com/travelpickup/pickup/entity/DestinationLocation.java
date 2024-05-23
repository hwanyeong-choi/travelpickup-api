package com.travelpickup.pickup.entity;

import com.travelpickup.pickup.dto.request.PickupLocationDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "destination_location")
public class DestinationLocation {

	@Id
	@Column(name = "destination_location_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long destinationLocationId;

	@Column(name = "pickup_id", nullable = false)
	private Long pickupId;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = " address_detail", nullable = false)
	private String addressDetail;

	@Column(name = "latitude", columnDefinition = "DECIMAL(10, 8)", nullable = false)
	private Double latitude;

	@Column(name = "longitude", columnDefinition = "DECIMAL(10, 8)", nullable = false)
	private Double longitude;

	@Builder
	public DestinationLocation(Long pickupId, String address, String addressDetail, Double latitude, Double longitude) {
		this.pickupId = pickupId;
		this.address = address;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static DestinationLocation of(PickupLocationDto pickupLocationDto, Long pickupId) {
		return DestinationLocation.builder()
			.pickupId(pickupId)
			.address(pickupLocationDto.getAddress())
			.addressDetail(pickupLocationDto.getAddressDetail())
			.latitude(pickupLocationDto.getLatitude())
			.longitude(pickupLocationDto.getLongitude())
			.build();
	}

	public static DestinationLocation createEmpty() {
		return DestinationLocation.builder().build();
	}

}
