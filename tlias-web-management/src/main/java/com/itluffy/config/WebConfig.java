package com.itluffy.config;

import com.itluffy.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 配置类
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private InterceptorDemo interceptorDemo;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /*
     * 注册拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");  // 登录请求不拦截
    }
}
