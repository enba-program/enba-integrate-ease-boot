package com.enba.sms.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
@ConfigurationProperties(prefix = "ali-yun")
public class AlYunConfig implements InitializingBean {

  Logger log = LoggerFactory.getLogger(AlYunConfig.class);

  private String accessKeyId;

  private String accessKeySecret;

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  @Bean
  public IAcsClient getAcsClient() {
    DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
    return new DefaultAcsClient(profile);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("==============accessKeyId:{},accessKeySecret:{}==============", accessKeyId, accessKeySecret);
  }
}
