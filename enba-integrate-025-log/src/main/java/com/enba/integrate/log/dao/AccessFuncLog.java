package com.enba.integrate.log.dao;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccessFuncLog implements Serializable {

  /** 日志实体对象 */
  @Data
  @Accessors(chain = true)
  public static class Log {

    @ApiModelProperty("模块")
    String module;

    @ApiModelProperty("方法")
    String func;

    @ApiModelProperty("类名")
    String className;

    @ApiModelProperty("方法")
    String method;

    @ApiModelProperty("请求URL")
    String requestUri;

    @ApiModelProperty("用户代理")
    String userAgent;

    @ApiModelProperty("方法入参")
    String params;

    @ApiModelProperty("地址")
    String remoteAddr;

    @ApiModelProperty("成功标志")
    Boolean success;

    @ApiModelProperty("错误信息")
    String exception;

    @ApiModelProperty("方法执行时长毫秒")
    Long spendTime;

    @ApiModelProperty("返回值")
    String responseData;
  }
}
