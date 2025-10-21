package com.italo.qrcodegenerator.infra.aws;

import com.italo.qrcodegenerator.ports.StoragePorts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Component
public class S3StorageAdapter implements StoragePorts {

    private final S3Client s3Client;

    private final String bucketName;

    private final String region;

    public S3StorageAdapter(@Value("${aws-s3-bucket-name}") String bucketName, @Value("${aws-s3-region}") String region) {
        this.region = region;
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
                .region(Region.of(this.region))
                .build();
    }


    @Override
    public String uploadFile(byte[] filedata, String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(filedata));
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }

}
