package com.travelpickup.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelpickup.member.domain.TravelPickupManager;

@Repository
public interface TravelPickupManagerRepository extends JpaRepository<TravelPickupManager, Long> {

	Optional<TravelPickupManager> findByProviderId(Long providerId);

}
