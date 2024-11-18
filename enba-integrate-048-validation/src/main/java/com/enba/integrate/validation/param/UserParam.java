package com.enba.integrate.validation.param;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserParam {

  @NotNull(
      message = "id为空",
      groups = {SecondValidation.class})
  private Long id;

  @NotBlank(message = "username为空")
  @Size(min = 3, max = 20, message = "username长度必须在9位到18位")
  private String username;

  @NotBlank(message = "password为空")
  @Size(min = 9, max = 18, message = "password长度必须在9位到18位")
  private String password;

  private String confirmPassword;

  @Email(message = "email非法")
  private String email;

  // 分组校验
  public interface FirstValidation {}

  public interface SecondValidation {}
}
