package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupProductImgRepository extends JpaRepository<PickupProductImg, Long> { }
