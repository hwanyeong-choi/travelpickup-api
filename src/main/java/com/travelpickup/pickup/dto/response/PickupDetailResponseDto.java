package com.travelpickup.pickup.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "픽업 상세 Dto")
public class PickupDetailResponseDto {

	@Schema(description = "픽업")
	private PickupResponseDto pickup;

	@Schema(description = "숙소")
	private DestinationLocationResponseDto destinationLocation;

	@Schema(description = "픽업 물품")
	private List<PickupProductResponseDto> productList;

	@Schema(description = "픽업센터 접수 QrCode Url")
	private String qrUrl;

	@Builder
	public PickupDetailResponseDto(PickupResponseDto pickup, DestinationLocationResponseDto destinationLocation,
		List<PickupProductResponseDto> productList, String qrUrl) {
		this.pickup = pickup;
		this.destinationLocation = destinationLocation;
		this.productList = productList;
		this.qrUrl = qrUrl;
	}

	public static PickupDetailResponseDto of(PickupResponseDto pickup,
		DestinationLocationResponseDto descriptionLocation, List<PickupProductResponseDto> productResponseList,
		String qrUrl) {
		return PickupDetailResponseDto.builder()
			.pickup(pickup)
			.destinationLocation(descriptionLocation)
			.productList(productResponseList)
			.qrUrl(qrUrl)
			.build();
	}

}
