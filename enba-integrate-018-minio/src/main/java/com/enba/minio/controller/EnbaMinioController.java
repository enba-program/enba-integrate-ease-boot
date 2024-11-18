package com.enba.minio.controller;

import com.enba.minio.manager.FileManager;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/enba-minio")
public class EnbaMinioController {

  @Autowired
  private FileManager fileManager;

  /**
   * 上传文件
   */
  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, MinioException, InvalidKeyException {
    return fileManager.uploadFile(file);
  }

}
