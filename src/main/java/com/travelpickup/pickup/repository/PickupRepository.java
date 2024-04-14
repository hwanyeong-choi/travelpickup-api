package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> { }
