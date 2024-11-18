package com.enba.integrate.jwt.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtUser {

  private boolean valid;

  private String userId;

  private String role;

  public JwtUser() {
    this.valid = false;
  }
}
