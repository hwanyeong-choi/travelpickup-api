package com.travelpickup.pickup.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPickupResponseDto {

    private List<PickupResponseDto> inProgressPickList;
    private List<PickupResponseDto> finishPickupList;

    @Builder
    public MyPickupResponseDto(List<PickupResponseDto> inProgressPickList,
                               List<PickupResponseDto> finishPickupList) {
        this.inProgressPickList = inProgressPickList;
        this.finishPickupList = finishPickupList;
    }

    public static MyPickupResponseDto of(List<PickupResponseDto> inProgressPickList,
                                         List<PickupResponseDto> completedPickupList) {
        return MyPickupResponseDto
                .builder()
                .inProgressPickList(inProgressPickList)
                .finishPickupList(completedPickupList)
                .build();
    }

}
