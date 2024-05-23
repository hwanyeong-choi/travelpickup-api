package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.PickupCenter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "PickupCenter 정보 DTO")
public class PickupCenterResponseDto {

	@Schema(description = "픽업센터 아이디")
	private Long id;

	@Schema(description = "픽업센터명")
	private String name;

	@Schema(description = "픽업센터 주소")
	private String address;

	@Schema(description = "픽업센터 상세주소")
	private String addressDetail;

	@Schema(description = "위도")
	private Double latitude;

	@Schema(description = "경도")
	private Double longitude;

	@Builder
	public PickupCenterResponseDto(Long id, String name, String address, String addressDetail, Double latitude,
		Double longitude) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static PickupCenterResponseDto of(PickupCenter pickupCenter) {
		return PickupCenterResponseDto.builder()
			.id(pickupCenter.getPickupCenterId())
			.name(pickupCenter.getName())
			.address(pickupCenter.getAddress())
			.addressDetail(pickupCenter.getAddressDetail())
			.latitude(pickupCenter.getLatitude())
			.longitude(pickupCenter.getLongitude())
			.build();
	}

}
