package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.PickupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupProductRepository extends JpaRepository<PickupProduct, Long> {

    List<PickupProduct> findByPickupId(Long pickupId);

}
