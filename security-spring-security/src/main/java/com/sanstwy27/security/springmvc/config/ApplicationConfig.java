package com.sanstwy27.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */

@Configuration // === applicationContext.xml
@ComponentScan(basePackages = "com.sanstwy27.security.springmvc"
        ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
}
