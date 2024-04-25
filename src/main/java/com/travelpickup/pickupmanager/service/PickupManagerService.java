package com.travelpickup.pickupmanager.service;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.member.dto.LoginManager;
import com.travelpickup.member.dto.LoginUser;
import com.travelpickup.member.repository.TravelPickupUserRepository;
import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.entity.PickupCenter;
import com.travelpickup.pickup.repository.PickupCenterRepository;
import com.travelpickup.pickup.repository.PickupRepository;
import com.travelpickup.pickupmanager.error.PickupManagerErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
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

    @Transactional(readOnly = false)
    public void assignedCenterByPickup(LoginManager loginManager, Long pickupId) {

        // TODO 센터등록을 할 픽업을 조회한다.
        Optional<Pickup> optionalPickup = pickupRepository.findById(pickupId);

        // TODO 등록할 픽업이 존재하는지 확인한다.
        if (optionalPickup.isEmpty()) {
            throw new TravelPickupServiceException(PickupManagerErrorType.NOT_FOUND_PICKUP);
        }

        // TODO 등록할 픽업이 등록 가능한 상태인지 이미 센터등록이 마무리된건지 확인한다.
        if (optionalPickup.get().notPickupCenterRequestPossible()) {
            throw new TravelPickupServiceException(PickupManagerErrorType.ASSIGNED_PICKUP);
        }

        // TODO pickup을 센터에 등록한다.
        Pickup pickup = optionalPickup.get();
        pickup.updatePickupCenterId(loginManager.getCenterId());

    }

}
