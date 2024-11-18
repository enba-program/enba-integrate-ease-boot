package com.enba.oss.controller;

import cn.hutool.core.io.FileUtil;
import com.enba.oss.aliyun.AliOssFileUtils;
import java.io.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/enba-oss")
public class EnbaOssController {

  /**
   * 阿里云文件上传
   *
   * @param file
   * @return
   */
  @PostMapping("/ali-oss-upload")
  public String aliUploadFile(@RequestParam("file") MultipartFile file) {
    String url = AliOssFileUtils.uploadSingleFile(file);
    return "aliyunUploadFile success..." + url;
  }

  /**
   * 阿里云文件下载
   *
   * @param url
   * @return
   */
  @GetMapping("/ali-oss-download")
  public String aliDownloadSingleFile(String url) {
    File file = AliOssFileUtils.downloadSingleFile("");

    FileUtil.copyFilesFromDir(file, new File("D:\\tmp\\enba"), true);

    return "aliDownloadSingleFile success...";
  }


}
