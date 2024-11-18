package com.enba.integrate.preventretry.props;

import java.time.Duration;

public class PreventRetryProperties {

  /** 是否启用 */
  private boolean enabled = false;

  /** 缓存有效时间 */
  private Duration ttl = Duration.ofMinutes(3);

  /** 防重请求关键字 */
  private String preventKey = "prevent";

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Duration getTtl() {
    return ttl;
  }

  public void setTtl(Duration ttl) {
    this.ttl = ttl;
  }

  public String getPreventKey() {
    return preventKey;
  }

  public void setPreventKey(String preventKey) {
    this.preventKey = preventKey;
  }
}
