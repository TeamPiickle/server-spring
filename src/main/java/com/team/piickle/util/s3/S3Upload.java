package com.team.piickle.util.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class S3Upload {

    //    private String bucket;
    //
    //    private final AmazonS3 amazonS3;
    //
    //    public String upload(MultipartFile multipartFile) throws IOException {
    //        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
    //
    //        ObjectMetadata objMeta = new ObjectMetadata();
    //        objMeta.setContentLength(multipartFile.getInputStream().available());
    //
    //        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
    //
    //        return amazonS3.getUrl(bucket, s3FileName).toString();
    //    }
}
