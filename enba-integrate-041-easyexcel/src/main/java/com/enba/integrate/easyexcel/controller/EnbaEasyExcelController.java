package com.enba.integrate.easyexcel.controller;

import com.enba.integrate.easyexcel.entity.EnbaUser;
import com.enba.integrate.easyexcel.service.EnbaEasyExcelService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/enba-easy-excel")
public class EnbaEasyExcelController {

  @Autowired private EnbaEasyExcelService enbaEasyExcelService;

  /**
   * 导入Excel文件并解析为EnbaUser对象列表
   *
   * @param file 用户上传的Excel文件
   * @throws IOException 如果文件读取失败时抛出此异常
   */
  @PostMapping("/import")
  public void importExcel(@RequestPart("file") MultipartFile file) throws IOException {

    enbaEasyExcelService.importExcel(file);
  }

  /**
   * 导出数据为Excel文件的控制器方法
   *
   * @param httpServletResponse HTTP响应对象，用于包装响应信息
   * @throws IOException 如果发生输入输出异常
   */
  @GetMapping("/export")
  private void exportExcel(HttpServletResponse httpServletResponse) throws IOException {

    // 模拟从数据源获取数据
    List<EnbaUser> enbaUserList = new ArrayList<>();

    // 生成模拟数据
    for (int i = 0; i < 100; i++) {
      EnbaUser enbaUser = new EnbaUser();
      enbaUser.setName("恩爸编程" + i);
      enbaUser.setAge(30);
      enbaUser.setBirthday(LocalDateTime.now());
      enbaUserList.add(enbaUser);
    }

    // 调用服务将数据导出为Excel文件
    enbaEasyExcelService.exportExcel(httpServletResponse, enbaUserList, EnbaUser.class, "导出表名称");
  }

  /**
   * 导出Excel模板文件
   *
   * @param httpServletResponse HTTP响应对象，用于向客户端输出数据
   * @throws IOException 如果发生I/O错误时抛出异常
   */
  @GetMapping("/export-template")
  private void exportExcelTemplate(HttpServletResponse httpServletResponse) throws IOException {
    // 调用EnbaEasyExcelService的服务方法来导出Excel模板
    enbaEasyExcelService.exportExcelTemplate(httpServletResponse, EnbaUser.class, "导出模版表名称");
  }
}
