package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.DestinationLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationLocationRepository extends JpaRepository<DestinationLocation, Long> { }
