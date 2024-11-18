package com.enba.integrate.easyexcel.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface EnbaEasyExcelService {

  void importExcel(MultipartFile file) throws IOException;

  void exportExcel(
      HttpServletResponse httpServletResponse, List<?> data, Class<?> clazz, String fileName)
      throws IOException;

  void exportExcelTemplate(HttpServletResponse httpServletResponse, Class<?> clazz, String fileName)
      throws IOException;
}
