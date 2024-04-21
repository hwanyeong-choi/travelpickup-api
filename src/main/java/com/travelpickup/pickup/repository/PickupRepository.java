package com.travelpickup.pickup.repository;

import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {

    boolean existsByUserIdAndStateIn(Long userId, List<PickupState> pickupStateList);

    List<Pickup> findByUserId(Long userId);

    Optional<Pickup> findByUserIdAndPickupId(Long userId, Long pickupId);

}
