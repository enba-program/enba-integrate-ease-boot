package com.enba.oss.aliyun;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件操作工具类
 */
@Slf4j
public class AliOssFileUtils {

  /**
   * 单个文件上传
   *
   * @param file 文件
   * @return {@link String}成功返回 文件路径，失败返回null
   */
  public static String uploadSingleFile(MultipartFile file) {
    if (file == null) {
      log.error("单文件上传失败，文件为空");
      return null;
    }
    try {
      return AliOssUtil.upload(AliOssUtil.generateKey(file.getOriginalFilename()), file.getBytes());
    } catch (Exception e) {
      log.error("单文件上传异常【" + file + "】", e);
    }
    return null;
  }

  /**
   * 单个文件上传
   *
   * @param file 文件
   * @return {@link String} 成功返回 文件路径，失败返回null
   */
  public static String uploadSingleFile(File file) {
    if (file == null) {
      log.error("单文件上传失败，文件为空");
      return null;
    }
    try {
      return AliOssUtil.upload(AliOssUtil.generateKey(file.getName()), file);
    } catch (Exception e) {
      log.error("单文件上传异常【" + file + "】", e);
    }
    return null;
  }

  /**
   * 多文件文件上传
   *
   * @param fileList 文件列表
   * @return {@link List}<{@link String}>成功返回 文件路径集合，失败返回null
   */
  public static List<String> uploadMultipartFile(List<MultipartFile> fileList) {
    List<String> filePaths = new ArrayList<>();
    Optional.ofNullable(fileList).ifPresent(fl -> fl.forEach(f -> {
          try {
            filePaths.add(AliOssUtil.upload(AliOssUtil.generateKey(f.getOriginalFilename()), f.getBytes()));
          } catch (IOException e) {
            log.error("多文件上传异常【" + f + "】", e);
          }
        })
    );
    return filePaths;
  }

  /**
   * 下载阿里云文件到本地
   *
   * @param url      阿里云链接
   * @return {@link File}
   */
  public static File downloadSingleFile(String url) {
    try {
      return AliOssUtil.download2File(url);
    } catch (Exception e) {
      log.error("单文件下载异常【" + url + "】", e);
    }
    return null;
  }

  /**
   * 批量下载阿里云文件到本地
   *
   * @param urlList 阿里云链接集合
   * @param dir     下载目录
   * @return {@link List}<{@link File}>
   */
  public static List<File> downloadMultipartFile(List<String> urlList, String dir) {
    List<File> files = new ArrayList<>();
    Optional.ofNullable(urlList)
        .ifPresent(fl -> fl.forEach(f -> files.add(AliOssUtil.download2Dir(f, dir))));
    return files;
  }


  /**
   * 通过后缀名判断是否是某种文件
   *
   * @param fileName 文件名称
   * @param suffix   后缀
   * @return boolean
   */
  public static boolean isFileBySuffix(String fileName, String suffix) {
    if (StringUtils.isNoneBlank(fileName) && StringUtils.isNoneBlank(suffix)) {
      return fileName.endsWith(suffix.toLowerCase()) || fileName.endsWith(suffix.toUpperCase());
    }
    return false;
  }

  /**
   * 下载网络文件
   *
   * @param urlPath  url路径
   * @param saveDir  保存dir
   * @param fileName 文件名称
   * @return {@link File}
   */
  public static File downloadByUrlPath(String urlPath, String saveDir, String fileName) {
    if (StringUtils.isBlank(urlPath)) {
      log.error("下载网络文件失败，链接为空");
      return null;
    }
    try (InputStream ins = new URL(urlPath).openStream()) {
      Path target = Paths.get(saveDir, fileName);
      Files.createDirectories(target.getParent());
      Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
      return new File(saveDir + File.separator + fileName);
    } catch (IOException e) {
      log.error("下载网络文件异常", e);
    }
    return null;
  }
}
