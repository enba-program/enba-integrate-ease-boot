package com.enba.integrate.springretry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class RetryConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate template = new RetryTemplate();
        // 设置重试策略
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3); // 最多重试3次
        template.setRetryPolicy(retryPolicy);

        // 设置回退策略
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000); // 每次重试之间等待1秒
        template.setBackOffPolicy(backOffPolicy);

        return template;
    }
}