package com.travelpickup.common.service;

import com.amazonaws.services.s3.AmazonS3;
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
            File file = convertMultiPartToFile(multipartFile);
            pickupImagePathList.add(uploadFileToS3(file, PICKUP_IMG_FILE_PREFIX + pickupId));
        }

        return pickupImagePathList;

    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String uploadFileToS3(File file, String fileName) {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        amazonS3.putObject(new PutObjectRequest(PICKUP_IMAGE_BUCKET, uniqueFileName, file));
        return amazonS3.getUrl(PICKUP_IMAGE_BUCKET, uniqueFileName).toString();
    }

}
