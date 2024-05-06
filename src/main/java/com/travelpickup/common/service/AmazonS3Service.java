package com.travelpickup.common.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AmazonS3Service {

    private final AmazonS3 amazonS3;

    private final String PICKUP_IMAGE_BUCKET = "pickupimg";

    private final String PICKUP_IMG_FILE_PREFIX = "pickup_";


    public AmazonS3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public List<String> uploadPickupImageFile(List<MultipartFile> multipartFileList,
                                              Long pickupId) throws IOException {

        List<String> pickupImagePathList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFileList) {
            pickupImagePathList.add(uploadFileToS3(multipartFile, PICKUP_IMG_FILE_PREFIX + pickupId));
        }

        return pickupImagePathList;

    }

    private String uploadFileToS3(MultipartFile multipartFile, String fileName) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(PICKUP_IMAGE_BUCKET, uniqueFileName, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(PICKUP_IMAGE_BUCKET, uniqueFileName).toString();
    }

}
