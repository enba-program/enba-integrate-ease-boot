/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.enba.integrate.knife4j.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig {

  @Value("${spring.application.name:}")
  private String applicationName;

  @Value("${app.version}")
  private String version;

  private final OpenApiExtensionResolver openApiExtensionResolver;

  public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
    this.openApiExtensionResolver = openApiExtensionResolver;
  }

  @Bean(value = "api")
  public Docket api() {
    String groupName = applicationName;
    Docket docket = new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .groupName(groupName)
        .select()
        //这里指定Controller扫描包路径
        //.apis(RequestHandlerSelectors.basePackage("com.example.testknife4j6.controller"))
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any())
        .build()
        .extensions(openApiExtensionResolver.buildExtensions(groupName));
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(" title for the API")
        .description("api description")
        .termsOfServiceUrl("url to the terms of service")
        .contact(new Contact("恩爸编程", "url", "email"))
        .version("1.0.0")
        .build();
  }

}
