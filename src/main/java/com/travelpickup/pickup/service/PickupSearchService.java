package com.travelpickup.pickup.service;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.pickup.dto.response.*;
import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.enums.PickupState;
import com.travelpickup.pickup.error.PickpServiceErrorType;
import com.travelpickup.pickup.repository.DestinationLocationRepository;
import com.travelpickup.pickup.repository.PickupCenterRepository;
import com.travelpickup.pickup.repository.PickupProductRepository;
import com.travelpickup.pickup.repository.PickupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PickupSearchService {

    private final PickupRepository pickupRepository;

    private final PickupCenterRepository pickupCenterRepository;

    private final DestinationLocationRepository destinationLocationRepository;

    private final PickupProductRepository pickupProductRepository;

    private final String IN_PROGRESS_PICKUP_KEY = "IN_PROGRESS_PICKUP_KEY";

    private final String FINISH_PICKUP_KEY = "FINISH_PICKUP_KEY";

    private final String QR_PICKUP_URL_PREFIX = "http://localhost:8080/api/v1/pickup/manager";


    public PickupSearchService(PickupRepository pickupRepository,
                               PickupCenterRepository pickupCenterRepository,
                               DestinationLocationRepository destinationLocationRepository,
                               PickupProductRepository pickupProductRepository) {
        this.pickupRepository = pickupRepository;
        this.pickupCenterRepository = pickupCenterRepository;
        this.destinationLocationRepository = destinationLocationRepository;
        this.pickupProductRepository = pickupProductRepository;
    }

    @Transactional(readOnly = true)
    public boolean isAlreadyProgress(Long userId) {
        return pickupRepository.existsByUserIdAndStateIn(userId, PickupState.getAlreadyProgressStateList());
    }

    @Transactional(readOnly = true)
    public MyPickupResponseDto getMyPickup(Long userId) {

        List<PickupResponseDto> pickupResponseDtoList = pickupRepository.findByUserId(userId)
                .stream()
                .map(PickupResponseDto::of)
                .toList();

        Map<String, List<PickupResponseDto>> pickupResponseMap =
                pickupResponseDtoList.stream().collect(Collectors.groupingBy(pickupResponseDto -> {
                            if(PickupState.getFinishStateList().contains(pickupResponseDto.getState())) return FINISH_PICKUP_KEY;
                            return IN_PROGRESS_PICKUP_KEY;}));

        return MyPickupResponseDto.of(pickupResponseMap.getOrDefault(IN_PROGRESS_PICKUP_KEY, Collections.emptyList()),
                        pickupResponseMap.getOrDefault(FINISH_PICKUP_KEY, Collections.emptyList()));

    }

    @Transactional(readOnly = true)
    public PickupDetailResponseDto getPickup(Long userId, Long pickupId) {

        Optional<Pickup> optionalPickup = getPickupByUserIdAndPickupId(userId, pickupId);

        if (optionalPickup.isEmpty()) {
            throw new TravelPickupServiceException(PickpServiceErrorType.INVALID_PICKUP_ID);
        }

        PickupResponseDto pickup = PickupResponseDto.of(optionalPickup.get());

        DestinationLocation destinationLocation = destinationLocationRepository
                .findByPickupId(pickupId)
                .orElse(DestinationLocation.createEmpty());

        DestinationLocationResponseDto destinationLocationResponseDto = DestinationLocationResponseDto.of(destinationLocation);

        List<PickupProductResponseDto> pickupProductResponseDtoList = pickupProductRepository.findByPickupId(pickupId)
                .stream()
                .map(PickupProductResponseDto::of)
                .toList();

        return PickupDetailResponseDto.of(
                pickup,
                destinationLocationResponseDto,
                pickupProductResponseDtoList,
                QR_PICKUP_URL_PREFIX + pickup.getId()
        );

    }

    @Transactional(readOnly = true)
    public Optional<Pickup> getPickupByUserIdAndPickupId(Long userId, Long pickupId) {
        return pickupRepository.findByUserIdAndPickupId(userId, pickupId);
    }

}



