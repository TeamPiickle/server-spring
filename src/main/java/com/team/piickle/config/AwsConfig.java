package com.team.piickle.config;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@RequiredArgsConstructor
public class AwsConfig {

//    @Value("${cloud.aws.credentials.accessKey}")
//    private String accessKey;
//    @Value("${cloud.aws.credentials.secretKey}")
//    private String secretKey;
//    @Value("${cloud.aws.region.static}")
//    private String region;
//
//    @Bean
//    public AmazonS3 amazonS3() {
//        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//        return AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
//    }
}
