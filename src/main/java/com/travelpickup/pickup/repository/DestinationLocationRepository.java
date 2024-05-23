package com.travelpickup.pickup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.pickup.entity.DestinationLocation;

@Repository
public interface DestinationLocationRepository extends JpaRepository<DestinationLocation, Long> {

	Optional<DestinationLocation> findByPickupId(Long pickupId);

}
