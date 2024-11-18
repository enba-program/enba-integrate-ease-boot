### logback日志

[官方文档](https://logback.qos.ch/documentation.html)

--- 

#### logback.xml跟logback-spring.xml有区别吗？
logback.xml 和 logback-spring.xml 在功能上都是用于配置 Logback 的日志行为，但它们在 Spring Boot 应用程序中的处理方式有所不同。以下是两者的区别：
~~~
1. logback.xml
标准 Logback 配置文件：这是 Logback 的标准配置文件，它用于所有使用 Logback 的 Java 应用程序。
位置：通常位于 src/main/resources 目录下。
用途：定义了 Logback 的所有配置细节，包括 Appender、Encoder、Logger 等。
优先级：当存在 logback.xml 文件时，Logback 会直接加载并使用该文件中的配置，而不考虑其他配置文件。

2. logback-spring.xml
Spring Boot 特有的配置文件：这是 Spring Boot 专门为 Logback 设计的配置文件，它在 Spring Boot 应用程序中具有特殊的意义。
位置：同样位于 src/main/resources 目录下。
用途：logback-spring.xml 可以包含额外的 Spring 配置，如环境变量的注入、自动配置等功能。
优先级：当存在 logback-spring.xml 文件时，Spring Boot 会优先加载并使用该文件中的配置，而不是 logback.xml。
特殊功能

logback-spring.xml 支持以下特殊的 Spring Boot 功能：
Spring Environment：
可以访问 Spring 的 Environment，从而更容易地从 Spring 的配置属性中获取值。
例如，可以使用 ${spring.application.name} 获取 Spring 应用程序的名字。
Spring Profile：
支持 Spring Profiles，可以根据不同的环境配置不同的日志策略。
例如，开发环境和生产环境可以有不同的日志级别或输出目标。
自动配置：
Spring Boot 提供了一些默认的自动配置，例如日志文件的滚动策略、日志级别等。
你可以覆盖这些默认配置。
~~~

##### 示例对比 logback.xml
```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

##### 示例对比 logback-spring.xml
```xml
<configuration debug="false" status="WARN">
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="${LOG_LEVEL:INFO}">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
--- 
##### 日志级别
~~~
Logback 支持多个日志级别，这些级别继承自 SLF4J（Simple Logging Facade for Java）。日志级别的选择取决于你想要记录的信息的重要性和详细程度。以下是 Logback 支持的主要日志级别及其使用场景：
~~~

- OFF
描述：关闭所有日志记录。
使用场景：在不需要任何日志记录的情况下使用，通常用于性能测试或者调试时完全关闭日志输出。
- ERROR
描述：记录错误信息，通常表示应用程序出现了严重的错误，可能导致部分或全部功能不可用。
使用场景：当应用程序发生异常或出现错误情况时，应该记录 ERROR 级别的日志。这些日志有助于诊断问题。
- WARN (WARNING)
描述：记录警告信息，表示可能出现的问题，但目前还不影响应用程序的正常运行。
使用场景：当检测到潜在的问题或者配置不当的情况时，应该记录 WARN 级别的日志。这些日志提醒开发者注意潜在的问题。
- INFO
描述：记录普通信息，用于跟踪应用程序的状态和操作。
使用场景：记录应用程序的启动、关闭和其他重要操作的信息。这些日志可以帮助监控应用程序的运行状态。
- DEBUG
描述：记录详细的调试信息，用于诊断问题。
使用场景：在开发和测试阶段，可以开启 DEBUG 级别的日志来帮助查找问题。这些日志通常包含详细的内部流程信息。
- TRACE
描述：记录最详细的调试信息，比 DEBUG 级别更细粒度。
使用场景：主要用于非常详细的内部流程跟踪，通常只在开发阶段启用。这些日志可以用来深入了解系统的内部工作原理。

~~~  
日志级别的层次关系
日志级别的层次关系是从低到高依次为：TRACE < DEBUG < INFO < WARN < ERROR < OFF。这意味着如果你设置了某个级别的日志，那么该级别及以上的所有日志都会被记录。例如，如果你设置了日志级别为 INFO，那么 INFO、WARN、ERROR 级别的日志都会被记录，而 DEBUG 和 TRACE 级别的日志则不会被记录。
~~~

##### 设置日志级别
```xml
在 Logback 中，你可以通过配置文件来设置日志级别。例如，在 logback.xml 或 logback-spring.xml 文件中，你可以这样设置根日志级别：
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
##### 动态设置日志级别
```properties
你还可以通过环境变量或者 Spring Boot 的配置属性来动态设置日志级别，例如：
logging.level.org.example=DEBUG
这会将 org.example 包下的所有日志级别设置为 DEBUG。
```

##### 指定加载xml位置
```yml
logging:
  config: classpath:abc/logback-spring.xml

不指定默认加载位置resources/logback-spring.xml或resources/logback.xml
```

