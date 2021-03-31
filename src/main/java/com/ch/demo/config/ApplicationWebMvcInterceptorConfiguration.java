package com.ch.demo.config;

import com.ch.demo.web.interceptors.LogActionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMvcInterceptorConfiguration implements WebMvcConfigurer {

    private final LogActionInterceptor logActionInterceptor;

    public ApplicationWebMvcInterceptorConfiguration(LogActionInterceptor logActionInterceptor) {
        this.logActionInterceptor = logActionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logActionInterceptor);
    }
}
