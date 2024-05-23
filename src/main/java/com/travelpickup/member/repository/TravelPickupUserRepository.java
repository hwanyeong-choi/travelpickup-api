package com.travelpickup.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelpickup.member.domain.TravelPickupUser;

public interface TravelPickupUserRepository extends JpaRepository<TravelPickupUser, Long> {

	Optional<TravelPickupUser> findByProviderId(Long providerId);

}
