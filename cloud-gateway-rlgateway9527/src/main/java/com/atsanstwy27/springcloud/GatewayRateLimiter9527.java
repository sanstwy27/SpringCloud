package com.atsanstwy27.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @author Sanstwy27
 * @create 8/15/2020
 * @ref https://piotrminkowski.com/2019/11/15/rate-limiting-in-spring-cloud-gateway-with-redis/
 */
@SpringBootApplication
public class GatewayRateLimiter9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayRateLimiter9527.class, args);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just("1");
    }
}
