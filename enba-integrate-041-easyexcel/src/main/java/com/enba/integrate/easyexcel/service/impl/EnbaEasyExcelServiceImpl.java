package com.enba.integrate.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.enba.integrate.easyexcel.entity.EnbaUser;
import com.enba.integrate.easyexcel.service.EnbaEasyExcelService;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EnbaEasyExcelServiceImpl implements EnbaEasyExcelService {

  @Override
  public void importExcel(MultipartFile file) throws IOException {
    // 使用EasyExcel读取文件流并解析为EnbaUser对象列表
    List<EnbaUser> enbaUserList =
        EasyExcel.read(new BufferedInputStream(file.getInputStream()))
            .head(EnbaUser.class)
            .sheet()
            .doReadSync();

    // 打印解析后的用户列表 TODO 拿到导入数据，结合自己业务处理
    System.out.println(enbaUserList);
  }

  /**
   * 导出数据到Excel文件并提供下载
   *
   * @param httpServletResponse HTTP响应对象，用于向客户端返回数据
   * @param data 要导出的数据列表
   * @param clazz 数据的类类型，用于反射生成Excel时的数据结构
   * @param fileName 导出的Excel文件名
   * @throws IOException 如果发生输入输出异常
   */
  @Override
  public void exportExcel(
      HttpServletResponse httpServletResponse, List<?> data, Class<?> clazz, String fileName)
      throws IOException {

    // 设置HTTP响应的内容类型为Excel文件
    httpServletResponse.setContentType(
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    // 设置HTTP响应的字符编码为UTF-8
    httpServletResponse.setCharacterEncoding("utf-8");

    // 对文件名进行URL编码，以确保文件名在下载时保持正确
    fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

    // 设置HTTP响应头，指定内容处置类型为附件，filename*表示文件名使用UTF-8编码
    httpServletResponse.setHeader(
        "Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

    // 使用EasyExcel库将数据写入到HTTP响应的输出流中，实现Excel文件的导出
    EasyExcel.write(httpServletResponse.getOutputStream(), clazz).sheet("Sheet").doWrite(data);
  }

  /**
   * 导出Excel模板
   *
   * @param httpServletResponse HTTP响应对象，用于向客户端输出数据
   * @param clazz 模板中数据的类类型，用于定义Excel数据的结构
   * @param fileName 导出文件的名称
   * @throws IOException 如果发生I/O异常
   */
  @Override
  public void exportExcelTemplate(
      HttpServletResponse httpServletResponse, Class<?> clazz, String fileName) throws IOException {
    // 创建一个空的列表，用于存放示例数据
    List<Object> list = new ArrayList<>();
    // 设置HTTP响应的内容类型，指定为Excel文件格式
    httpServletResponse.setContentType(
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    // 设置HTTP响应的字符编码
    httpServletResponse.setCharacterEncoding("utf-8");
    // 对文件名进行编码，以处理中文文件名问题
    fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
    // 设置HTTP响应头，指定文件下载而非网页显示，并使用UTF-8编码的文件名
    httpServletResponse.setHeader(
        "Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    // 向HTTP响应的输出流中写入Excel数据，使用EasyExcel框架进行写操作
    EasyExcel.write(httpServletResponse.getOutputStream(), clazz).sheet("Sheet1").doWrite(list);
  }
}
