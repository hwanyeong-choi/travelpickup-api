package com.travelpickup.pickup.service;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.common.service.AmazonS3Service;
import com.travelpickup.pickup.dto.request.PickUpRegisterRequestDto;
import com.travelpickup.pickup.entity.*;
import com.travelpickup.pickup.enums.PickupState;
import com.travelpickup.pickup.error.PickpServiceErrorType;
import com.travelpickup.pickup.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PickupService {

    private final PickupSearchService pickupSearchService;

    private final PickupRepository pickupRepository;

    private final DestinationLocationRepository destinationLocationRepository;

    private final PickupProductRepository pickupProductRepository;

    private final PickupProductImgRepository pickupProductImgRepository;

    private final AmazonS3Service amazonS3Service;

    public PickupService(PickupSearchService pickupSearchService,
                         PickupRepository pickupRepository,
                         DestinationLocationRepository destinationLocationRepository,
                         PickupCenterRepository pickupCenterRepository,
                         PickupProductRepository pickupProductRepository,
                         PickupProductImgRepository pickupProductImgRepository,
                         AmazonS3Service amazonS3Service) {
        this.pickupSearchService = pickupSearchService;
        this.pickupRepository = pickupRepository;
        this.destinationLocationRepository = destinationLocationRepository;
        this.pickupProductRepository = pickupProductRepository;
        this.pickupProductImgRepository = pickupProductImgRepository;
        this.amazonS3Service = amazonS3Service;
    }

    @Transactional(readOnly = false)
    public void pickupSave(PickUpRegisterRequestDto pickUpRegisterRequestDto,
                           List<MultipartFile> pickupProductsPhotoFiles,
                           Long userId) throws IOException {

        boolean isAlreadyProgress = pickupSearchService.isAlreadyProgress(userId);

        if (isAlreadyProgress) {
            throw new TravelPickupServiceException(PickpServiceErrorType.PICKUP_ALREADY_IN_PROGRESS);
        }

        Pickup pickup = Pickup.of(userId);
        Pickup savePickup = pickupRepository.save(pickup);

        DestinationLocation destinationLocation = DestinationLocation.of(pickUpRegisterRequestDto.getDescriptionLocation(), savePickup.getPickupId());
        List<PickupProduct> pickupProductList = pickUpRegisterRequestDto.getPickupProductDtoList()
                .stream()
                .map(it -> PickupProduct.of(it, savePickup.getPickupId()))
                .toList();

        destinationLocationRepository.save(destinationLocation);
        pickupProductRepository.saveAll(pickupProductList);

        if (Boolean.FALSE.equals(CollectionUtils.isEmpty(pickupProductsPhotoFiles))) {

            List<PickupProductImg> pickupProductImgList = amazonS3Service
                    .uploadPickupImageFile(pickupProductsPhotoFiles, pickup.getPickupId())
                    .stream()
                    .map(path -> PickupProductImg.of(savePickup.getPickupId(), path))
                    .toList();

            pickupProductImgRepository.saveAll(pickupProductImgList);

        }

    }

    @Transactional(readOnly = false)
    public void getPickupCancelAble(Long userId, Long pickupId) {
        Optional<Pickup> optionalPickup = pickupRepository.findByUserIdAndPickupId(userId, pickupId);

        if (optionalPickup.isEmpty()) {
            throw new TravelPickupServiceException(PickpServiceErrorType.INVALID_PICKUP_ID);
        }

        if (optionalPickup.get().notCancelAble()) {
            throw new TravelPickupServiceException(PickpServiceErrorType.CANNOT_CANCEL_PICKUP);
        }

        Pickup pickup = optionalPickup.get();
        pickup.updatePickupState(PickupState.PICKUP_CANCEL);

    }

}
