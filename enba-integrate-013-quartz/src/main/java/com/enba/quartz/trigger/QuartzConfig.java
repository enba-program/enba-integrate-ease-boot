package com.enba.quartz.trigger;

import com.enba.quartz.job.MyJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

  @Bean
  public JobDetail myJobDetail() {
    return JobBuilder.newJob(MyJob.class)
        .withIdentity("myjob")
        .storeDurably()
        .build();
  }

  @Bean
  public Trigger myJobTrigger() {
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

    return TriggerBuilder.newTrigger()
        .forJob(myJobDetail())
        .withIdentity("demoJob02Trigger")
        .withSchedule(scheduleBuilder)
        .build();
  }
}