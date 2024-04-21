package com.travelpickup.pickup.entity;

import com.travelpickup.pickup.dto.request.PickupLocationDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "pickup_location")
public class PickupLocation {

    @Id
    @Column(name = "pickup_location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickupLocationId;

    @Column(name = "pickup_id")
    private Long pickupId;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude", columnDefinition = "DECIMAL(10, 8")
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "DECIMAL(10, 8")
    private Double longitude;

    @Builder
    public PickupLocation(Long pickupId,
                          String description,
                          Double latitude,
                          Double longitude) {
        this.pickupId = pickupId;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static PickupLocation of(PickupLocationDto pickupLocationDto, Long pickupId) {
        return PickupLocation
                .builder()
                .pickupId(pickupId)
                .description(pickupLocationDto.getDescription())
                .latitude(pickupLocationDto.getLatitude())
                .longitude(pickupLocationDto.getLongitude())
                .build();
    }

}
