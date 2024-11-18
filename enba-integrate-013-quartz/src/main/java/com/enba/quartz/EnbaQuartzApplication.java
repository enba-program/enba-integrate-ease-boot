package com.enba.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 这个注解是可选的，用于启用基于注解的调度功能
public class EnbaQuartzApplication {

  /**
   * <p style="color:red">Quartz Scheduler提供了多种类型的Trigger，用于控制Job的执行时机和频率。以下是Quartz中主要的几种默认Trigger类型</p>
   * <p>SimpleTrigger：这是最简单的Trigger类型，它可以指定Job执行的次数（可以是一次性或多次）以及每次执行之间的间隔。SimpleTrigger适用于那些不需要复杂调度模式的任务。</p>
   * <p>CronTrigger：CronTrigger使用类似于Unix cron任务的表达式来定义复杂的执行计划。这种Trigger允许你按分钟、小时、天、月、周等单位定义Job的执行时间。Cron表达式由六个或七个字段组成，分别表示秒、分、小时、日、月、周和年（可选）。</p>
   * <p>CalendarIntervalTrigger：这种Trigger允许你按照日历间隔来安排Job的执行，例如每天、每周、每月等。它比SimpleTrigger更灵活，因为它可以考虑日历周期。</p>
   * <p>BlobTrigger：BlobTrigger是一个比较特殊的Trigger，它允许你存储二进制大对象(BLOB)数据，这些数据可以被Job使用。这种Trigger通常不用于常规的调度目的，而是用于需要传递额外信息给Job的场景。</p>
   * <p>JobTrigger：这个概念实际上并不对应于Quartz中的具体Trigger类型，但它指出了Trigger和Job之间的关系。每个Trigger都关联着一个或多个Job，JobTrigger这个术语有时用于描述这种关联。</p>
   * <p>JobTrigger：这个概念实际上并不对应于Quartz中的具体Trigger类型，但它指出了Trigger和Job之间的关系。每个Trigger都关联着一个或多个Job，JobTrigger这个术语有时用于描述这种关联。</p>
   */

  /*存储方式分为RAM和JDBC，本案例演示作用，采用内存模式*/
  public static void main(String[] args) {
    SpringApplication.run(EnbaQuartzApplication.class, args);
  }
}
