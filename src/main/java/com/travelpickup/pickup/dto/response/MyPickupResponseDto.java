package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPickupResponseDto {

    private List<PickupResponseDto> inProgressPickList;
    private List<PickupResponseDto> completedPickupList;

    @Builder
    public MyPickupResponseDto(List<PickupResponseDto> inProgressPickList,
                               List<PickupResponseDto> completedPickupList) {
        this.inProgressPickList = inProgressPickList;
        this.completedPickupList = completedPickupList;
    }

    public static MyPickupResponseDto of(List<PickupResponseDto> inProgressPickList,
                                         List<PickupResponseDto> completedPickupList) {
        return MyPickupResponseDto
                .builder()
                .inProgressPickList(inProgressPickList)
                .completedPickupList(completedPickupList)
                .build();
    }

}
