package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPickupResponseDto {

    private List<PickupResponseDto> inProgressPickupList;
    private List<PickupResponseDto> finishPickupList;

    @Builder
    public MyPickupResponseDto(List<PickupResponseDto> inProgressPickupList,
                               List<PickupResponseDto> finishPickupList) {
        this.inProgressPickupList = inProgressPickupList;
        this.finishPickupList = finishPickupList;
    }

    public static MyPickupResponseDto of(List<PickupResponseDto> inProgressPickList,
                                         List<PickupResponseDto> completedPickupList) {
        return MyPickupResponseDto
                .builder()
                .inProgressPickupList(inProgressPickList)
                .finishPickupList(completedPickupList)
                .build();
    }

}
