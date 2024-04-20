package com.travelpickup.pickup.service;

import com.travelpickup.common.exception.TravelPickupServiceException;
import com.travelpickup.common.service.AmazonS3Service;
import com.travelpickup.pickup.dto.PickUpRegisterRequestDto;
import com.travelpickup.pickup.entity.*;
import com.travelpickup.pickup.error.PickpServiceErrorType;
import com.travelpickup.pickup.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PickupService {

    private final PickupSearchService pickupSearchService;

    private final PickupRepository pickupRepository;

    private final DestinationLocationRepository destinationLocationRepository;

    private final PickupLocationRepository pickupLocationRepository;

    private final PickupProductRepository pickupProductRepository;

    private final PickupProductImgRepository pickupProductImgRepository;

    private final AmazonS3Service amazonS3Service;

    public PickupService(PickupSearchService pickupSearchService,
                         PickupRepository pickupRepository,
                         DestinationLocationRepository destinationLocationRepository,
                         PickupLocationRepository pickupLocationRepository,
                         PickupProductRepository pickupProductRepository,
                         PickupProductImgRepository pickupProductImgRepository,
                         AmazonS3Service amazonS3Service) {
        this.pickupSearchService = pickupSearchService;
        this.pickupRepository = pickupRepository;
        this.destinationLocationRepository = destinationLocationRepository;
        this.pickupLocationRepository = pickupLocationRepository;
        this.pickupProductRepository = pickupProductRepository;
        this.pickupProductImgRepository = pickupProductImgRepository;
        this.amazonS3Service = amazonS3Service;
    }

    @Transactional(readOnly = false)
    public void pickupSave(PickUpRegisterRequestDto pickUpRegisterRequestDto,
                           List<MultipartFile> pickupProductsPhotoFiles,
                           Long userId) throws IOException {

        boolean isInProgress = pickupSearchService.isInProgress(userId);

        if (isInProgress) {
            throw new TravelPickupServiceException(PickpServiceErrorType.PICKUP_ALREADY_IN_PROGRESS);
        }

        Pickup pickup = Pickup.of(pickUpRegisterRequestDto, userId);
        Pickup savePickup = pickupRepository.save(pickup);

        PickupLocation pickupLocation = PickupLocation.of(pickUpRegisterRequestDto.getPickupLocation(), savePickup.getPickupId());
        DestinationLocation destinationLocation = DestinationLocation.of(pickUpRegisterRequestDto.getDescriptionLocation(), savePickup.getPickupId());
        List<PickupProduct> pickupProductList = pickUpRegisterRequestDto.getPickupProductDtoList()
                .stream()
                .map(it -> PickupProduct.of(it, savePickup.getPickupId()))
                .toList();

        pickupLocationRepository.save(pickupLocation);
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

}
