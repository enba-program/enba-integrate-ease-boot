### RestTemplate(HTTP调用)

> **RestTemplate 是 Spring 提供的一个用于执行 HTTP 请求的便捷工具类。它简化了与 RESTful Web 服务的交互过程，支持多种 HTTP 方法（GET, POST, PUT, DELETE等），并且可以很容易地设置请求头、请求体以及处理响应。**
---

#### RestTemplateBuilder 推荐使用

##### 1.基本使用

```java
RestTemplate restTemplate=new RestTemplateBuilder().build();
```

#### 2.设置默认请求头

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .defaultHeader("Accept","application/json")
    .defaultHeader("User-Agent","MyApp/1.0")
    .build();
```

#### 3.设置消息转换器

```java
ObjectMapper objectMapper=new ObjectMapper();
    MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter(objectMapper);

    RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .messageConverters(converter)
    .build();
```

#### 4.设置错误处理器

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .errorHandler(new MyCustomErrorHandler())
    .build();
```

#### 5.设置连接超时和读取超时

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .setConnectTimeout(Duration.ofMillis(2000))
    .setReadTimeout(Duration.ofMillis(3000))
    .build();
```

#### 6.设置基础URI

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .build();
```

#### 7.设置日志级别

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .logLevel(RestTemplateBuilder.LogLevel.BASIC)
    .build();
```

#### 8.设置请求工厂

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .requestFactory(()->new MyCustomRequestFactory())
    .build();
```

#### 9.设置自定义拦截器

```java
RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("http://api.example.com/")
    .build();

    String result=restTemplate.execute("http://api.example.com/data",HttpMethod.GET,
    request->{
    // 在这里可以添加你的请求前处理逻辑
    request.getHeaders().add("X-Custom-Header","CustomHeaderValue");
    },response->{
    // 在这里可以添加你的响应后处理逻辑
    return EntityUtils.toString(response.getBody());
    });
```

#### 10.设置SSL配置

```java
SSLContext sslContext=SSLContexts.custom()
    .loadTrustMaterial(null,TrustSelfSignedStrategy.INSTANCE)
    .build();

    RestTemplate restTemplate=new RestTemplateBuilder()
    .rootUri("https://api.example.com/")
    .sslContext(sslContext)
    .build();
```

#### 总结

~~~
RestTemplateBuilder 提供了一种更加灵活和强大的方式来配置RestTemplate实例。通过上述方法，你可以根据自己的需求轻松地设置各种配置选项，从而更好地满足你的HTTP请求需求。
~~~


