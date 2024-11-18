package com.enba.integrate.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.Map;

public class EnbaExcelListener extends AnalysisEventListener<Object> {

  @Override
  public void invoke(Object data, AnalysisContext analysisContext) {
    // 输出每行数据的回调处理结果 TODO 可以结合自己的业务来处理
    System.out.println("每行回调：" + data);
  }

  /**
   * 打印表头数据
   *
   * <p>此方法在处理Excel数据时，用于接收并打印工作表的表头部分它通过接受一个Map类型的参数，
   * 其中Key为Integer类型，代表表头的索引，Value为String类型，代表表头的名称这样可以帮助我们快速识别各个列的含义
   *
   * @param headMap 包含表头索引和名称的Map集合
   * @param context 分析上下文，提供有关当前解析操作的信息
   */
  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    System.out.println("表头数据：" + headMap);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 当所有数据解析完成后，输出提示信息 TODO 结合自己的业务处理
    System.out.println("读取Excel完毕");
  }
}
