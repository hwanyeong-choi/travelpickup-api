package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupProductImgRepository extends JpaRepository<PickupProductImg, Long> {

    List<PickupProductImg> findByPickupId(Long pickupId);

}
