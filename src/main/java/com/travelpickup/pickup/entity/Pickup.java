package com.travelpickup.pickup.entity;

import com.travelpickup.common.util.DateConvertUtils;
import com.travelpickup.pickup.enums.PickupState;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.travelpickup.pickup.enums.PickupState.PICKUP_REQUEST_COMPLETED;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup")
public class Pickup {

    @Id
    @Column(name = "pickup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickupId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "state", columnDefinition = "VARCHAR(100)", nullable = false)
    @Enumerated(EnumType.STRING)
    private PickupState state;

    @Column(name = "center_id", nullable = true)
    private Long centerId;

    @Column(name = "create_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "modify_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modifyAt;

    @Builder
    public Pickup(Long userId,
                  PickupState state) {
        this.userId = userId;
        this.state = state;
    }

    public static Pickup of(Long userId) {
        return Pickup
                .builder()
                .userId(userId)
                .state(PICKUP_REQUEST_COMPLETED)
                .build();
    }

    public void updatePickupState(PickupState pickupState) {
        this.state = pickupState;
    }

    public void updatePickupCenterId(Long centerId) {
        this.centerId = centerId;
        this.state = PickupState.PICKUP_CENTER_REQUEST_COMPLETED;
    }

    public String getMyPickupFormatCreateAt() {
        return DateConvertUtils.localDateConvert(this.createAt, DateConvertUtils.YYYY_DOT_MM_DOT_DD_PATTERN);
    }

    public boolean notCancelAble() {
        return !PICKUP_REQUEST_COMPLETED.equals(this.state);
    }

    public boolean notPickupCenterRequestPossible() {
        return !PICKUP_REQUEST_COMPLETED.equals(this.state);
    }


}
