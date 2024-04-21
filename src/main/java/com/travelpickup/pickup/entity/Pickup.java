package com.travelpickup.pickup.entity;

import com.travelpickup.common.util.DateConvertUtils;
import com.travelpickup.pickup.enums.PickupState;
import com.travelpickup.pickup.dto.request.PickUpRegisterRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup")
public class Pickup {

    @Id
    @Column(name = "pickup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickupId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "state", columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private PickupState state;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "modify_at")
    @UpdateTimestamp
    private LocalDateTime modifyAt;

    @Builder
    public Pickup(Long userId,
                  PickupState state) {
        this.userId = userId;
        this.state = state;
    }

    public static Pickup of(PickUpRegisterRequestDto pickUpRegisterRequestDto, Long userId) {
        return Pickup
                .builder()
                .userId(userId)
                .state(PickupState.PICKUP_MAN_MATCHING)
                .build();
    }

    public void updatePickupState(PickupState pickupState) {
        this.state = pickupState;
    }

    public String getMyPickupFormatCreateAt() {
        return DateConvertUtils.localDateConvert(this.createAt, DateConvertUtils.YYYY_DOT_MM_DOT_DD_PATTERN);
    }

    public boolean notCancelAble() {
        return !PickupState.PICKUP_MAN_MATCHING.equals(this.state);
    }

}
