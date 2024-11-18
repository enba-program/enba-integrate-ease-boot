package com.enba.integrate.i18n.model;

import java.util.ResourceBundle;
import lombok.Data;

@Data
public class I18nResource {

  private String language;

  private ResourceBundle resourceBundle;
}
