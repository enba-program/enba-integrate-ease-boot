package com.enba.oss.aliyun;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;


/**
 * aliyun oss工具类
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss.file")
@Slf4j
public class AliOssUtil {

  /**
   * 存储空间地址
   */
  private static String bucket = "";

  /**
   * 域名
   */
  private static String ossEndPoint = "";

  /**
   * 账号
   */
  private static String ossAccessKeyId = "";

  /**
   * 密匙
   */
  private static String ossAccessKeySecret = "";

  /**
   * 存储空间名称
   */
  private static String ossBucketName = "";

  /**
   * URL有效期
   */
  private final static Date OSS_URL_EXPIRATION;

  static {
    OSS_URL_EXPIRATION = localDateTimeToDate(LocalDateTime.now().plusYears(1));
  }


  /**
   * LocalDateTime转Date
   *
   * @param localDateTime
   * @return
   */
  public static Date localDateTimeToDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }


  private volatile static OSS instance = null;

  /**
   * Oss 实例化
   *
   * @return {@link OSS}
   */
  private static OSS getOssClient() {
    if (instance == null) {
      synchronized (AliOssUtil.class) {
        if (instance == null) {
          instance = new OSSClientBuilder()
              .build(ossEndPoint, ossAccessKeyId, ossAccessKeySecret);
        }
      }
    }
    return instance;
  }

  /**
   * 生成密钥
   *
   * @param fileName 文件名称
   * @return {@link String}
   */
  public static String generateKey(String fileName) {
    //对象名称格式：yyyyMMddHHmmssSSS.ext
    return new StringBuilder(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"))
        .append(".").append(FilenameUtils.getExtension(fileName))
        .toString();
  }


  /**
   * 上传
   *
   * @param key  键
   * @param file 文件
   * @return {@link String}
   */
  public static String upload(String key, File file) {
    if (file == null || !file.exists()) {
      log.error("阿里云上传文件失败【" + file + "】不存在");
      return null;
    }
    log.info("阿里云上传文件开始：【" + file + "】");
    OSS ossClient = getOssClient();
    try {
      ossClient.putObject(ossBucketName, key, file);
      String url = ossClient.generatePresignedUrl(ossBucketName, key, OSS_URL_EXPIRATION)
          .toString();
      log.info("阿里云上传文件结束：【" + file + "】=>【" + url + "】");
      return url;
    } catch (Exception e) {
      log.error("阿里云上传文件异常【" + file + "】", e);
    } finally {
      ossClient.shutdown();
    }
    return null;
  }

  /**
   * 上传
   *
   * @param key   键
   * @param bytes 字节
   * @return {@link String}
   */
  public static String upload(String key, byte[] bytes) {
    if (bytes == null || StringUtils.isBlank(key)) {
      log.error("阿里云上传文件不存在:【" + key + "】");
      return null;
    }
    log.info("阿里云上传文件开始:【" + key + "】");
    OSS ossClient = getOssClient();
    try {
      ossClient.putObject(ossBucketName, key, new ByteArrayInputStream(bytes));
      //设置url过期时间
      String url = ossClient.generatePresignedUrl(ossBucketName, key, OSS_URL_EXPIRATION)
          .toString();
      log.info("阿里云上传文件结束：【" + key + "】=>【" + url + "】");
      return url;
    } catch (Exception e) {
      log.error("阿里云上传文件异常:【" + key + "】", e);
    } finally {
      ossClient.shutdown();
    }
    return null;
  }

  /**
   * 传方法，返回对象名称和 url
   *
   * @param file 文件
   * @return {@link Map}<{@link String}, {@link String}>
   */
  public static Map<String, String> uploadWithObjectName(File file) {
    if (file == null || !file.exists()) {
      log.error("阿里云上传文件失败【" + file + "】不存在");
      return null;
    }
    Map<String, String> map = new HashMap<>();
    log.info("阿里云上传文件开始：【" + file + "】");
    OSS ossClient = getOssClient();
    try {
      String key = generateKey(file.getName());

      ossClient.putObject(ossBucketName, key, new FileInputStream(file));
      //设置url过期时间
      String url = ossClient.generatePresignedUrl(ossBucketName, key, OSS_URL_EXPIRATION)
          .toString();
      log.info("阿里云上传文件结束：【" + file + "】=>【" + url + "】");
      map.put("objectName", key);
      map.put("url", url);
      return map;
    } catch (Exception e) {
      log.error("阿里云上传文件异常【" + file + "】", e);
    } finally {
      ossClient.shutdown();
    }
    return null;
  }

  /**
   * 删除方法
   *
   * @param url url
   */
  public static void delete(String url) {
    if (StringUtils.isBlank(url)) {
      log.error("阿里云删除文件失败，对象url为空");
      return;
    }
    log.info("阿里云删除文件开始：【" + url + "】");
    if (url.contains(bucket)) {
      //根据url获取对象名称
      url = getObjectNameByUrl(url);
    }
    OSS ossClient = getOssClient();
    try {
      // 删除文件
      ossClient.deleteObject(ossBucketName, url);
      log.info("阿里云删除文件结束：【" + url + "】");
    } catch (Exception e) {
      log.error("阿里云删除文件异常【" + url + "】", e);
    } finally {
      ossClient.shutdown();
    }
  }

  /**
   * 下载文件到本地文件
   *
   * @param url 待下载对象url
   * @return {@link File}
   */
  public static File download2File(String url) {
    log.info("阿里云下载文件开始：【" + url + "】");
    if (url.contains(bucket)) {
      //根据url获取对象名称
      url = getObjectNameByUrl(url);
    }
    OSS ossClient = getOssClient();
    try {
      File touch = FileUtil.touch(url);
      // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
      ossClient.getObject(new GetObjectRequest(ossBucketName, url), touch);
      log.info("阿里云下载文件结束：【" + url + "】");
      return touch;
    } catch (Exception e) {
      log.error("阿里云下载文件异常【" + url + "】", e);
    } finally {
//            ossClient.shutdown();
    }
    return null;
  }

  /**
   * 通过流下载文件
   *
   * @param url      待下载对象url
   * @param fileName 文件名称
   * @param response 响应
   */
  public static void download2FileByStream(String url, String fileName,
      HttpServletResponse response) {
    BufferedInputStream inputStream = null;
    OSS ossClient = getOssClient();
    try (
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());) {
      // 配置文件下载
      response.setHeader("content-type", "application/octet-stream");
      response.setContentType("application/octet-stream");
      if (url.contains(bucket)) {
        //根据url获取对象名称
        url = getObjectNameByUrl(url);
      }
      // 下载文件能正常显示中文
      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder
          .encode(StringUtils.isBlank(fileName) ? url : fileName, "UTF-8"));
      log.info("阿里云下载文件开始：【" + url + "】");
      // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
      OSSObject ossObject = ossClient.getObject(bucket, url);
      inputStream = new BufferedInputStream(ossObject.getObjectContent());
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = inputStream.read(buff, 0, buff.length))) {
        outputStream.write(buff, 0, bytesRead);
      }
      outputStream.flush();
    } catch (Exception e) {
      log.error("下载异常！", e);
    } finally {
      log.info("阿里云下载文件结束：【" + url + "】");
      ossClient.shutdown();
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 下载文件到指定目录,文件名称为阿里云文件对象名称
   *
   * @param url 待下载对象url
   * @param dir 下载到本地目录
   * @return {@link File}
   */
  public static File download2Dir(String url, String dir) {
    log.info("阿里云下载文件开始：【" + url + "】");
    if (url.contains(bucket)) {
      //根据url获取对象名称
      url = getObjectNameByUrl(url);
    }
    OSS ossClient = getOssClient();
    try {
      File file = new File(dir + File.separator + url);
      // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
      ossClient.getObject(new GetObjectRequest(ossBucketName, url), file);
      log.info("阿里云下载文件结束：【" + url + "】");
      return file;
    } catch (Exception e) {
      log.error("阿里云下载文件异常【" + url + "】", e);
    } finally {
      ossClient.shutdown();
    }
    return null;
  }

  /**
   * 通过url获取对象名称
   *
   * @param url url
   * @return {@link String}
   */
  public static String getObjectNameByUrl(String url) {
    if (StringUtils.isBlank(url)) {
      return null;
    }
    return url.substring(url.indexOf(bucket) + bucket.length() + 1);
  }

  /**
   * 重载方法,根据file生成文件名称并且上传文件到阿里云
   *
   * @param file : MultipartFile文件
   * @return 数据库中要存入的路径
   * @see #upload(String, byte[])
   */
  public static String upload(MultipartFile file) throws IOException {
    if (file == null || Strings.isEmpty(file.getOriginalFilename())) {
      return null;
    }
    return upload(generateKey(file.getOriginalFilename()), file.getBytes());
  }


  /**
   * 调用浏览器下载
   *
   * @param url      服务器文件url
   * @param response 响应
   * @param name     下载名字
   */
  public static void download2FileByStream(String url, HttpServletResponse response, String name) {

    File file = new File(url);
    String fileName = file.getName();
    fileName = StringUtils.substringBefore(fileName, "?");
    String fileLast = StringUtils.substringAfterLast(fileName, ".");

    fileName = name + "." + fileLast;

    BufferedInputStream inputStream = null;
    OSS ossClient = getOssClient();
    try (
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());) {
      // 配置文件下载
      response.setHeader("content-type", "application/octet-stream");
      response.setContentType("application/octet-stream");
      if (url.contains(bucket)) {
        //根据url获取对象名称
        url = getObjectNameByUrl(url);
      }
      // 下载文件能正常显示中文
      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder
          .encode(StringUtils.isBlank(fileName) ? url : fileName, "UTF-8"));
      log.info("阿里云下载文件开始：【" + url + "】");
      // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
      OSSObject ossObject = ossClient.getObject(ossBucketName, url);
      inputStream = new BufferedInputStream(ossObject.getObjectContent());
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = inputStream.read(buff, 0, buff.length))) {
        outputStream.write(buff, 0, bytesRead);
      }
      outputStream.flush();
    } catch (Exception e) {
      log.error("下载异常！", e);
    } finally {
      log.info("阿里云下载文件结束：【" + url + "】");
      ossClient.shutdown();
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}