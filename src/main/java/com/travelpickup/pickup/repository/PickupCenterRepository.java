package com.travelpickup.pickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.pickup.entity.PickupCenter;

@Repository
public interface PickupCenterRepository extends JpaRepository<PickupCenter, Long> {

}
