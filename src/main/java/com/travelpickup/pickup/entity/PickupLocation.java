package com.travelpickup.pickup.entity;

import com.travelpickup.pickup.dto.PlaceLocationDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "pickup_location")
@NoArgsConstructor
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

    public static PickupLocation of(PlaceLocationDto placeLocationDto, Long pickupId) {
        return PickupLocation
                .builder()
                .pickupId(pickupId)
                .description(placeLocationDto.getDescription())
                .latitude(placeLocationDto.getLatitude())
                .longitude(placeLocationDto.getLongitude())
                .build();
    }

}
