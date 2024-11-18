### Eureka Client

~~~
这是一个独立的服务，用于管理和维护微服务架构中的服务实例注册与发现。
~~~

---

#### 依赖

```xml

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
</dependencies>

<dependencyManagement>
<dependencies>
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>2020.0.5</version>
    <type>pom</type>
    <scope>import</scope>
  </dependency>
</dependencies>
</dependencyManagement>
```

启动类上加注解：**@EnableEurekaClient**

主要的配置类：**EurekaClientConfigBean**

注意：Spring Cloud是基于Spring Boot技术为基础的，在技术选型时，版本不是随便选择的，否则可能会造成很多意料之外的错误

[boot&cloud版本对应关系](https://spring.io/projects/spring-cloud)
