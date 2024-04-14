package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupLocationRepository extends JpaRepository<PickupLocation, Long> { }
