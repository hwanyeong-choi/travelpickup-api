package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupProductRepository extends JpaRepository<PickupProduct, Long> { }
