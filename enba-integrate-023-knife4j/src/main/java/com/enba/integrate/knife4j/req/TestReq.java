package com.enba.integrate.knife4j.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "请求入参")
public class TestReq {

  @ApiModelProperty(value = "姓名")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
