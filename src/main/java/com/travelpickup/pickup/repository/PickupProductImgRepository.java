package com.travelpickup.pickup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.pickup.entity.PickupProductImg;

@Repository
public interface PickupProductImgRepository extends JpaRepository<PickupProductImg, Long> {

	Optional<PickupProductImg> findByPickupProductId(Long pickupProductId);

}
