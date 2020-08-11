package com.atsanstwy27.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sanstwy27
 * @create 8/11/2020
 */
@Configuration
public class MySelfRibbonRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
