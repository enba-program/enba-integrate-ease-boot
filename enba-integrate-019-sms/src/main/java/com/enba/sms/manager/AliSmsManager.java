package com.enba.sms.manager;

import com.alibaba.fastjson2.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 阿里云 发送短信manager
 */
@Component
public class AliSmsManager {

  Logger log = LoggerFactory.getLogger(AliSmsManager.class);

  @Autowired
  private IAcsClient acsClient;

  /**
   * 发送短信
   *
   * @param phoneNumber  手机号
   * @param templateCode 模版code
   * @param smsCode      验证码
   * @throws ClientException
   */
  public void sendSms(
      String phoneNumber, String templateCode, String smsCode) {
    CommonRequest request = new CommonRequest();
    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");
    request.putQueryParameter("PhoneNumbers", phoneNumber);

    // TODO 短信签名名称 替换成自己的
    request.putQueryParameter("SignName", "短信签名");

    // TODO 短信模版code 替换成自己的
    request.putQueryParameter("TemplateCode", templateCode);
    request.putQueryParameter("TemplateParam", "{\"code\":\"" + smsCode + "\"}");

    try {
      CommonResponse response = acsClient.getCommonResponse(request);
      log.info("acsClient.getCommonResponse(request) response:{}", JSON.toJSONString(response));

      AliSendSmsResult result = JSON.parseObject(response.getData(), AliSendSmsResult.class);

      if (!"OK".equals(result.getCode())) {
        if (StringUtils.isNotBlank(result.getMessage()) && result.getMessage().contains("流控")) {
          throw new RuntimeException("验证码获取次数已达上限，请次日重试");
        } else {
          throw new RuntimeException(result.getMessage());
        }
      }
    }catch (Exception ex){
      log.error("");
    }

  }

}
