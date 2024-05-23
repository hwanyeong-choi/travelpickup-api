package com.travelpickup.pickupmanager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.member.repository.TravelPickupUserRepository;
import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.repository.PickupCenterRepository;
import com.travelpickup.pickup.repository.PickupRepository;
import com.travelpickup.pickupmanager.error.PickupManagerErrorType;
import com.travelpickup.secutiry.dto.LoginManager;

@Service
@Transactional(readOnly = true)
public class PickupManagerService {

	private final TravelPickupUserRepository travelPickupUserRepository;

	private final PickupRepository pickupRepository;

	private final PickupCenterRepository pickupCenterRepository;

	public PickupManagerService(TravelPickupUserRepository travelPickupUserRepository,
		PickupRepository pickupRepository, PickupCenterRepository pickupCenterRepository) {
		this.travelPickupUserRepository = travelPickupUserRepository;
		this.pickupRepository = pickupRepository;
		this.pickupCenterRepository = pickupCenterRepository;
	}

	@Transactional(readOnly = false)
	public void assignedCenterByPickup(LoginManager loginManager, Long pickupId) {

		Optional<Pickup> optionalPickup = pickupRepository.findById(pickupId);

		if (optionalPickup.isEmpty()) {
			throw new TravelPickupServiceException(PickupManagerErrorType.NOT_FOUND_PICKUP);
		}

		if (optionalPickup.get().notPickupCenterRequestPossible()) {
			throw new TravelPickupServiceException(PickupManagerErrorType.ASSIGNED_PICKUP);
		}

		Pickup pickup = optionalPickup.get();
		pickup.updatePickupCenterId(loginManager.getCenterId());

	}

}
