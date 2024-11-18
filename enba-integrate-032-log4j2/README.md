### log4j2日志

[官方文档](https://logging.apache.org/log4j/2.x/)

--- 

##### 步骤 1: 添加依赖

~~~
首先，你需要在项目的 pom.xml 文件中添加 Log4j2 的依赖。Spring Boot 默认使用 Logback，所以你需要排除 Logback 依赖，并添加 Log4j2 依赖。
~~~

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
      <exclusion>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-logging</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
  </dependency>
</dependencies>
```

##### 步骤 2: 配置 Log4j2

~~~
在 src/main/resources 目录下创建 log4j2.xml 文件，用于配置 Log4j2。
~~~

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>

    <RollingFile name="RollingFile" fileName="logs/app.log"
      filePattern="logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
      <PatternLayout>
        <pattern>%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n</pattern>
      </PatternLayout>
      <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="10 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingFile>
  </Appenders>

  <Loggers>
    <Root level="info">
      <AppenderRef ref="Console"/>
      <AppenderRef ref="RollingFile"/>
    </Root>

    <Logger name="com.example" level="debug" additivity="false">
      <AppenderRef ref="Console"/>
      <AppenderRef ref="RollingFile"/>
    </Logger>
  </Loggers>
</Configuration>
```

##### 详细解释

###### 排除 Logback

- 通过在 spring-boot-starter 依赖中排除 spring-boot-starter-logging，防止 Spring Boot 自动引入 Logback。

###### 添加 Log4j2 依赖

- 添加 log4j-api 和 log4j-core 依赖，这两个是 Log4j2 的核心库。
- 添加 log4j-slf4j-impl 依赖，用于将 SLF4J API 转换为 Log4j2。

###### 配置文件 log4j2.xml

- Console Appender 用于将日志输出到控制台。
- RollingFile Appender 用于将日志输出到文件，并按时间和大小滚动日志文件。
- Root Logger 设置全局的日志级别为 info，并使用两个 Appender。
- Logger 定义了一个名为 com.example 的 Logger，用于特定包的日志记录，并设置日志级别为 debug。

###### 使用日志记录

- 在应用程序中使用 SLF4J2 API 来记录日志。
