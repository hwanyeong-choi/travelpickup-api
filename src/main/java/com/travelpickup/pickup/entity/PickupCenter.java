package com.travelpickup.pickup.entity;

import com.travelpickup.pickupmanager.dto.PickupCenterRegisterRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup_center")
public class PickupCenter {

    @Id
    @Column(name = "pickup_center_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickupCenterId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude", columnDefinition = "DECIMAL(10, 8")
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "DECIMAL(10, 8")
    private Double longitude;

    @Builder
    public PickupCenter(String name,
                        String description,
                        Double latitude,
                        Double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static PickupCenter of(PickupCenterRegisterRequestDto pickupCenterRegisterRequestDto) {
        return PickupCenter
                .builder()
                .name(pickupCenterRegisterRequestDto.getName())
                .description(pickupCenterRegisterRequestDto.getDescription())
                .latitude(pickupCenterRegisterRequestDto.getLatitude())
                .longitude(pickupCenterRegisterRequestDto.getLongitude())
                .build();
    }

    public static PickupCenter createEmpty() {
        return PickupCenter.builder().build();
    }

}
