package com.travelpickup.member.repository;

import com.travelpickup.member.domain.TravelPickupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelPickupUserRepository extends JpaRepository<TravelPickupUser, Long> {

    Optional<TravelPickupUser> findByProviderId(Long providerId);

}
