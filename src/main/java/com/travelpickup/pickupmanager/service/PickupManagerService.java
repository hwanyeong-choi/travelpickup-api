package com.travelpickup.pickupmanager.service;

import com.travelpickup.member.repository.TravelPickupUserRepository;
import com.travelpickup.pickup.entity.PickupCenter;
import com.travelpickup.pickup.repository.PickupCenterRepository;
import com.travelpickup.pickup.repository.PickupRepository;
import org.springframework.stereotype.Service;

@Service
public class PickupManagerService {

    private final TravelPickupUserRepository travelPickupUserRepository;

    private final PickupRepository pickupRepository;

    private final PickupCenterRepository pickupCenterRepository;

    public PickupManagerService(TravelPickupUserRepository travelPickupUserRepository,
                                PickupRepository pickupRepository,
                                PickupCenterRepository pickupCenterRepository) {
        this.travelPickupUserRepository = travelPickupUserRepository;
        this.pickupRepository = pickupRepository;
        this.pickupCenterRepository = pickupCenterRepository;
    }

}
