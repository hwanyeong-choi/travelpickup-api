package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PickupProductImgRepository extends JpaRepository<PickupProductImg, Long> {

    Optional<PickupProductImg> findByPickupProductId(Long pickupProductId);

}
