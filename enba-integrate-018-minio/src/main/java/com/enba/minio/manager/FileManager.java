package com.enba.minio.manager;

import com.alibaba.fastjson2.JSON;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

  Logger log = LoggerFactory.getLogger(FileManager.class);

  private final MinioClient minioClient;
  private final String bucketName;

  public FileManager(MinioClient minioClient,
      @Value("${minio.bucket-name}") String bucketName) {
    this.minioClient = minioClient;
    this.bucketName = bucketName;
  }


  /**
   * 上传文件
   *
   * @param file 文件
   * @return
   * @throws IOException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws MinioException
   */
  public String uploadFile(MultipartFile file)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, MinioException {
    ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
        .bucket(bucketName)
        .object(file.getOriginalFilename())
        .stream(file.getInputStream(), file.getSize(), -1)
        .contentType(file.getContentType())
        .build());

    log.info("uploadFile:{}", JSON.toJSONString(objectWriteResponse));
    return "File uploaded successfully";
  }

  // 可以添加其他方法如下载、删除等
}