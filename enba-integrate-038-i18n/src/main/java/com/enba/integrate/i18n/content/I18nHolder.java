package com.enba.integrate.i18n.content;

import com.enba.integrate.i18n.model.I18nResource;

public class I18nHolder {

  private static final ThreadLocal<I18nResource> THREAD_CONTEXT = new ThreadLocal<>();

  public static I18nResource source() {
    return THREAD_CONTEXT.get();
  }

  public static void putResourceBundle(I18nResource resourceBundle) {
    THREAD_CONTEXT.set(resourceBundle);
  }

  public static void clear() {
    THREAD_CONTEXT.remove();
  }
}
