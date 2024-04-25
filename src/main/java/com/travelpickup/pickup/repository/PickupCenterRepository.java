package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupCenterRepository extends JpaRepository<PickupCenter, Long> {

}
