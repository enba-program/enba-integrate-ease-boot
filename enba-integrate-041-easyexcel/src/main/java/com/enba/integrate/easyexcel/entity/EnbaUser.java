package com.enba.integrate.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EnbaUser {

  @ExcelProperty(index = 0, value = "姓名")
  private String name;

  @ExcelProperty(index = 1, value = "年龄")
  private Integer age;

//  @DateTimeFormat("yyyy-MM-dd")
  @ExcelProperty(index = 2, value = "生日")
  private LocalDateTime birthday;
}
