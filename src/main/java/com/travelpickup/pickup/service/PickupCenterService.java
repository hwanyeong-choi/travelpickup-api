package com.travelpickup.pickup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.pickup.dto.response.PickupCenterResponseDto;
import com.travelpickup.pickup.entity.PickupCenter;
import com.travelpickup.pickup.error.PickupCenterServiceErrorType;
import com.travelpickup.pickup.repository.PickupCenterRepository;

@Service
@Transactional(readOnly = true)
public class PickupCenterService {

	private final PickupCenterRepository pickupCenterRepository;

	public PickupCenterService(PickupCenterRepository pickupCenterRepository) {
		this.pickupCenterRepository = pickupCenterRepository;
	}

	@Transactional(readOnly = true)
	public List<PickupCenterResponseDto> getPickupCenterList() {
		return pickupCenterRepository.findAll().stream().map(PickupCenterResponseDto::of).toList();
	}

	@Transactional(readOnly = true)
	public PickupCenterResponseDto getPickupCenterById(Long pickupCenterId) {
		Optional<PickupCenter> optionalPickupCenter = pickupCenterRepository.findById(pickupCenterId);

		if (optionalPickupCenter.isEmpty()) {
			throw new TravelPickupServiceException(PickupCenterServiceErrorType.INVALID_PICKUP_CENTER_ID);
		}

		return PickupCenterResponseDto.of(optionalPickupCenter.get());

	}

}
