### Apollo(阿波罗分布式配置中心)

[官方文档](https://www.apolloconfig.com/#/zh/README)

~~~
Apollo（阿波罗）是一款可靠的分布式配置管理中心，诞生于携程框架研发部，能够集中化管理应用不同环境、不同集群的配置，配置修改后能够实时推送到应用端，并且具备规范的权限、流程治理等特性，适用于微服务配置管理场景。
~~~

```txt
Apollo支持4个维度管理Key-Value格式的配置：

application (应用)
environment (环境)
cluster (集群)
namespace (命名空间)
同时，Apollo基于开源模式开发，开源地址：https://github.com/ctripcorp/apollo
```

####  搭建集成流程

- 第一步：安装和启动 Apollo
下载 Apollo: 访问 Apollo 官方 GitHub 页面 或者通过 Maven 构建工具获取 Apollo 的源码。
构建 Apollo: 使用 Maven 或者 IDE 的内置Maven插件构建 Apollo 项目。
运行 Apollo: 根据官方文档的指示运行 Apollo，通常需要先启动数据库（Apollo 推荐使用 MySQL），然后启动其他相关服务。
- 第二步：在 Spring Boot 应用中集成 Apollo