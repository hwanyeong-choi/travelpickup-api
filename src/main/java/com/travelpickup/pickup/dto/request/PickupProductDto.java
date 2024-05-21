package com.travelpickup.pickup.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "픽업신청 물품 Dto")
public class PickupProductDto {

    @Schema(description = "물품명")
    @NotNull(message = "값이 비어있을 수 없습니다.")
    private String name;

    @Schema(description = "물품개수")
    @NotNull(message = "값이 null일 수 없습니다.")
    @PositiveOrZero(message = "값은 0 이상이어야 합니다.")
    private Long quantity;

}
