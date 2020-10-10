package com.sanstwy27.security.springmvc.init;

import com.sanstwy27.security.springmvc.config.ApplicationConfig;
import com.sanstwy27.security.springmvc.config.WebConfig;
import com.sanstwy27.security.springmvc.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Sanstwy27
 * @create 10/10/2020
 */

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // spring container, === load applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    // servletContext, === load springmvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
