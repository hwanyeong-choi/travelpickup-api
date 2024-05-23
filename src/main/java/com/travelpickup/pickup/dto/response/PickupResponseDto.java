package com.travelpickup.pickup.dto.response;

import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "픽업 Dto")
public class PickupResponseDto {

	@Schema(description = "픽업 Id")
	private Long id;

	@Schema(description = "접수된 픽업센터 Id")
	private Long centerId;

	@Schema(description = "픽업 진행상태")
	private PickupState state;

	@Schema(description = "픽업 진행상태 view")
	private String viewState;

	@Schema(description = "픽업신청일")
	private String createAt;

	@Builder
	public PickupResponseDto(Long pickupId, Long centerId, PickupState state, String viewState, String createAt) {
		this.id = pickupId;
		this.centerId = centerId;
		this.state = state;
		this.viewState = viewState;
		this.createAt = createAt;
	}

	public static PickupResponseDto of(Pickup pickup) {
		return PickupResponseDto.builder()
			.pickupId(pickup.getPickupId())
			.centerId(pickup.getCenterId())
			.state(pickup.getState())
			.viewState(pickup.getState().getViewState())
			.createAt(pickup.getMyPickupFormatCreateAt())
			.build();
	}

}
