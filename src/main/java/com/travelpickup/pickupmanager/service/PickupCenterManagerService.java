package com.travelpickup.pickupmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelpickup.pickup.entity.PickupCenter;
import com.travelpickup.pickup.repository.PickupCenterRepository;
import com.travelpickup.pickupmanager.dto.PickupCenterRegisterRequestDto;

@Service
@Transactional(readOnly = true)
public class PickupCenterManagerService {

	private final PickupCenterRepository pickupCenterRepository;

	public PickupCenterManagerService(PickupCenterRepository pickupCenterRepository) {
		this.pickupCenterRepository = pickupCenterRepository;
	}

	@Transactional(readOnly = false)
	public void registerPickupCenter(PickupCenterRegisterRequestDto pickupCenterRegisterRequestDto) {
		PickupCenter pickupCenter = PickupCenter.of(pickupCenterRegisterRequestDto);
		pickupCenterRepository.save(pickupCenter);
	}

}
