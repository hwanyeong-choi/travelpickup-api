package com.travelpickup.pickupmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "픽업센터 등록 DTO")
public class PickupCenterRegisterRequestDto {

    @Schema(description = "픽업센터명")
    @NotBlank(message = "값이 비어있을 수 없습니다.")
    private String name;

    @Schema(description = "픽업센터 주소")
    @NotBlank(message = "값이 비어있을 수 없습니다.")
    private String address;

    @Schema(description = "픽업센터 상세 주소")
    @NotBlank(message = "값이 비어있을 수 없습니다.")
    private String addressDetail;

    @Schema(description = "픽업센터 위도")
    @NotNull(message = "값이 null일 수 없습니다.")
    @PositiveOrZero(message = "값은 0 이상이어야 합니다.")
    private Double latitude;

    @Schema(description = "픽업센터 경도")
    @NotNull(message = "값이 null일 수 없습니다.")
    @PositiveOrZero(message = "값은 0 이상이어야 합니다.")
    private Double longitude;

}
