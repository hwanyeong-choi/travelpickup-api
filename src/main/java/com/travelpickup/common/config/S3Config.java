package com.travelpickup.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Value("${cloud.aws.endpoint.url}")
	private String endpointUrl;

	@Bean
	public AmazonS3 amazonS3(AWSCredentialsProvider credentialsProvider) {
		return AmazonS3ClientBuilder.standard()
			.withCredentials(credentialsProvider)
			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, region))
			.withPathStyleAccessEnabled(true)
			.build();
	}

	@Bean
	public AWSCredentialsProvider awsCredentialsProvider() {
		return new DefaultAWSCredentialsProviderChain();
	}
}
