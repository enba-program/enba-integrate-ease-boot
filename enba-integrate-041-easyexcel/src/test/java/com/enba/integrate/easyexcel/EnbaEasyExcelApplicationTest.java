package com.enba.integrate.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.enba.integrate.easyexcel.entity.EnbaUser;
import com.enba.integrate.easyexcel.listener.EnbaExcelListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EnbaEasyExcelApplication.class)
public class EnbaEasyExcelApplicationTest {

  @Test
  public void writeDemo() {

    List<EnbaUser> enbaUserList = new ArrayList<>();
    for (int i = 0; i < 200; i++) {
      EnbaUser enbaUser = new EnbaUser();
      enbaUser.setName("恩爸编程" + i);
      enbaUser.setAge(30);
      enbaUser.setBirthday(LocalDateTime.now());
      enbaUserList.add(enbaUser);
    }

    String fileName = "C:\\Users\\DELL\\Desktop\\新建文件夹 (3)\\test.xlsx";
    EasyExcel.write(fileName, EnbaUser.class).sheet("学生信息表").doWrite(enbaUserList);
  }

  @Test
  void contextLoads() {

    EasyExcel.read(
            "C:\\Users\\DELL\\Desktop\\新建文件夹 (3)\\test.xlsx", EnbaUser.class, new EnbaExcelListener())
        .sheet()
        .doRead();
  }
}
