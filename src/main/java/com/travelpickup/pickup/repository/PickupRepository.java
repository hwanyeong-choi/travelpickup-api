package com.travelpickup.pickup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {

	boolean existsByUserIdAndStateIn(Long userId, List<PickupState> pickupStateList);

	List<Pickup> findByUserId(Long userId);

	Optional<Pickup> findByUserIdAndPickupId(Long userId, Long pickupId);

}
