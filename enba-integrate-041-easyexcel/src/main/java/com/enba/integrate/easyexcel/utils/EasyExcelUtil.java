package com.enba.integrate.easyexcel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EasyExcelUtil {

  public static Date formatDate(String birthday) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
    try {
      return sdf.parse(birthday);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
