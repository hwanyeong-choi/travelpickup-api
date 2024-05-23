package com.travelpickup.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelpickup.member.domain.TravelPickupUser;
import com.travelpickup.member.dto.TravelPickupUserMeResponseDto;
import com.travelpickup.member.repository.TravelPickupUserRepository;

@Service
@Transactional(readOnly = true)
public class TravelPickupUserService {

	private final TravelPickupUserRepository travelPickupUserRepository;

	public TravelPickupUserService(TravelPickupUserRepository travelPickupUserRepository) {
		this.travelPickupUserRepository = travelPickupUserRepository;
	}

	@Transactional(readOnly = true)
	public TravelPickupUserMeResponseDto getTravelPickupUserInfo(Long userId) {
		Optional<TravelPickupUser> optionalTravelPickupUser = travelPickupUserRepository.findById(userId);

		if (optionalTravelPickupUser.isPresent()) {
			return TravelPickupUserMeResponseDto.of(optionalTravelPickupUser.get());
		}

		throw new IllegalArgumentException("존재하지 않는 회원 입니다.");

	}

}
