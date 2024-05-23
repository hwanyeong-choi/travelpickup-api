package com.travelpickup.pickup.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "픽업신청 내역 조회 Dto")
public class MyPickupResponseDto {

	@Schema(description = "진행중인 픽업 Dto List")
	private List<PickupResponseDto> inProgressPickupList;

	@Schema(description = "종료된 픽업 Dto List")
	private List<PickupResponseDto> finishPickupList;

	@Builder
	public MyPickupResponseDto(List<PickupResponseDto> inProgressPickupList, List<PickupResponseDto> finishPickupList) {
		this.inProgressPickupList = inProgressPickupList;
		this.finishPickupList = finishPickupList;
	}

	public static MyPickupResponseDto of(List<PickupResponseDto> inProgressPickList,
		List<PickupResponseDto> completedPickupList) {
		return MyPickupResponseDto.builder()
			.inProgressPickupList(inProgressPickList)
			.finishPickupList(completedPickupList)
			.build();
	}

}
