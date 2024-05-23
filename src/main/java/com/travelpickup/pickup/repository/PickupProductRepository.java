package com.travelpickup.pickup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.pickup.entity.PickupProduct;

@Repository
public interface PickupProductRepository extends JpaRepository<PickupProduct, Long> {

	List<PickupProduct> findByPickupId(Long pickupId);

}
