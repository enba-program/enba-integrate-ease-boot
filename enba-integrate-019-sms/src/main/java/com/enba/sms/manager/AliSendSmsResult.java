package com.enba.sms.manager;

/**
 * 发送短信 阿里云响应实体
 */
public class AliSendSmsResult {

  private String Message;

  private String RequestId;

  private String BizId;

  private String Code;

  public String getMessage() {
    return Message;
  }

  public void setMessage(String Message) {
    this.Message = Message;
  }

  public String getRequestId() {
    return RequestId;
  }

  public void setRequestId(String RequestId) {
    this.RequestId = RequestId;
  }

  public String getBizId() {
    return BizId;
  }

  public void setBizId(String BizId) {
    this.BizId = BizId;
  }

  public String getCode() {
    return Code;
  }

  public void setCode(String Code) {
    this.Code = Code;
  }
}
