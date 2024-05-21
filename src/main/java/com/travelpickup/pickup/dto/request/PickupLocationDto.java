package com.travelpickup.pickup.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "숙소 위치 Dto")
public class PickupLocationDto {
    
    @Schema(description = "숙소 주소")
    @NotBlank(message = "값이 비어있을 수 없습니다.")
    private String address;

    @Schema(description = "숙소 상세 주소")
    private String addressDetail;

    @Schema(description = "숙소 위도")
    @NotNull(message = "값이 null일 수 없습니다.")
    @PositiveOrZero(message = "값은 0 이상이어야 합니다.")
    private Double latitude;

    @Schema(description = "숙소 경도")
    @NotNull(message = "값이 null일 수 없습니다.")
    @PositiveOrZero(message = "값은 0 이상이어야 합니다.")
    private Double longitude;

    public PickupLocationDto(String address,
                             String addressDetail,
                             Double latitude,
                             Double longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
