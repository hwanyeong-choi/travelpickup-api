package com.travelpickup.member.repository;

import com.travelpickup.member.domain.TravelPickupManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelPickupManagerRepository extends JpaRepository<TravelPickupManager, Long> {

    Optional<TravelPickupManager> findByProviderId(Long providerId);

}
