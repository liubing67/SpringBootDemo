package com.abing.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class WebAppConfig {

    @Autowired
    private LoginIntercept loginIntercept;

    @Bean
    public WebMvcConfigurer WebMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //注册拦截接口，拦截所有请求 基于方法 ，过滤器基于请求
                //拦截器师傅拦截静态资源？
                registry.addInterceptor(loginIntercept).addPathPatterns("/*");
            }
        };
    }
}
