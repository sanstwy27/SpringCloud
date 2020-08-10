package com.atsanstwy27.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sanstwy27
 * @create 8/10/2020
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    // Come up with OrderController -> Url.ServiceName
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
