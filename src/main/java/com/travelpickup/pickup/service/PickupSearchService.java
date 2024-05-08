package com.travelpickup.pickup.service;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.common.service.AmazonS3Service;
import com.travelpickup.pickup.dto.response.*;
import com.travelpickup.pickup.entity.DestinationLocation;
import com.travelpickup.pickup.entity.Pickup;
import com.travelpickup.pickup.entity.PickupProductImg;
import com.travelpickup.pickup.enums.PickupState;
import com.travelpickup.pickup.error.PickpServiceErrorType;
import com.travelpickup.pickup.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    private final PickupProductImgRepository pickupProductImgRepository;
    private final AmazonS3Service amazonS3Service;


    public PickupSearchService(PickupRepository pickupRepository,
                               PickupCenterRepository pickupCenterRepository,
                               DestinationLocationRepository destinationLocationRepository,
                               PickupProductRepository pickupProductRepository, PickupProductImgRepository pickupProductImgRepository, AmazonS3Service amazonS3Service) {
        this.pickupRepository = pickupRepository;
        this.pickupCenterRepository = pickupCenterRepository;
        this.destinationLocationRepository = destinationLocationRepository;
        this.pickupProductRepository = pickupProductRepository;
        this.pickupProductImgRepository = pickupProductImgRepository;
        this.amazonS3Service = amazonS3Service;
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

        if (!optionalPickup.get().getUserId().equals(userId)) {
            throw new TravelPickupServiceException(PickpServiceErrorType.INVALID_PICKUP_ID);
        }

        PickupResponseDto pickup = PickupResponseDto.of(optionalPickup.get());

        DestinationLocation destinationLocation = destinationLocationRepository
                .findByPickupId(pickupId)
                .orElse(DestinationLocation.createEmpty());

        DestinationLocationResponseDto destinationLocationResponseDto = DestinationLocationResponseDto.of(destinationLocation);

        List<PickupProductResponseDto> pickupProductResponseDtoList = pickupProductRepository.findByPickupId(pickupId)
                .stream()
                .map(pickupProduct -> {
                    Optional<PickupProductImg> optionalPickupProductImg
                            = pickupProductImgRepository.findByPickupProductId(pickupProduct.getPickupProductId());

                    if (optionalPickup.isPresent()) {
                        PickupProductImg pickupProductImg = optionalPickupProductImg.get();

                        try {
                            String pickupProductImgByBase64 = amazonS3Service.getPickupImageFile(pickupProductImg.getPath());
                            return PickupProductResponseDto.of(pickupProduct, pickupProductImgByBase64);
                        } catch (IOException e) {
                            return PickupProductResponseDto.of(pickupProduct);
                        }
                    }
                    return PickupProductResponseDto.of(pickupProduct);
                })
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
