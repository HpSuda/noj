package com.example.ojbackend.config;

import com.example.ojbackend.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())//使用注入容器的jwtInterceptor
                .addPathPatterns("/**/submit");  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                //.excludePathPatterns("/**/login", "/**/register", "/**/rank");
                //.excludePathPatterns("/**/submit");
    }
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
