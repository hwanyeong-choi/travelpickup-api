package com.travelpickup.pickup.service;

import com.travelpickup.pickup.enums.PickupState;
import com.travelpickup.pickup.repository.PickupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PickupSearchService {

    private final PickupRepository pickupRepository;


    public PickupSearchService(PickupRepository pickupRepository) {
        this.pickupRepository = pickupRepository;
    }

    @Transactional(readOnly = true)
    public boolean isInProgress(Long userId) {
        return pickupRepository.existsByUserIdAndStateIn(userId, PickupState.getInProgressStateList());
    }

}


