### xxl-job(分布式定时任务)

xxl-job是一款分布式任务调度平台，基于Spring Boot、Spring Cloud、Mybatis、Mybatis-Plus、Druid、Redis、Zookeeper等技术开发，
提供分布式任务调度、动态任务管理、执行日志、任务监控、任务报警等，同时支持任务强一致性调度和任务失效转移，可快速实现分布式任务调度。

官方文档：https://www.xuxueli.com/xxl-job/

本案例： enba-integrate-015-xxljobadmin为调度器

enba-integrate-015-xxljob-executor和enba-integrate-015-xxljob-executor为执行器
注意：他俩appname一样，可以在调度器编辑任务调整任务策略，感受下什么是分布式任务调度
