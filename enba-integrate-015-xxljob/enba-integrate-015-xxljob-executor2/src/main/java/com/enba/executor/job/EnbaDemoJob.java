package com.enba.executor.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class EnbaDemoJob {

  /**
   * 这个用的最多 1、简单任务示例（Bean模式）
   */
  @XxlJob("enbaJobHandler")
  public void demoJobHandler() throws Exception {
    XxlJobHelper.log("XXL-JOB, Hello World.");

    System.out.println("执行业务逻辑，注意幂等操作");

    for (int i = 0; i < 5; i++) {
      XxlJobHelper.log("beat at:" + i);
      TimeUnit.SECONDS.sleep(2);
    }
    // default success
  }


  /**
   * 传递参数
   *
   * @throws Exception
   */
  @XxlJob("enbaJobHandler2")
  public void demoJobHandler2() throws Exception {

    // param parse
    String param = XxlJobHelper.getJobParam();
    if (param == null || param.trim().length() == 0) {
      XxlJobHelper.log("param[" + param + "] invalid.");

      XxlJobHelper.handleFail();
      return;
    }

    System.out.println("打印参数：" + param);
    XxlJobHelper.handleSuccess();
  }


}
