package com.itluffy.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")  // 拦截所有请求
@Slf4j
public class FilterDemo implements Filter {

    // 初始化过滤器, 在容器启动时调用, 只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器 ...");
    }

    // 过滤请求, 在业务处理之前调用, 每次请求都会调用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到了请求 ...");
        // 放行, 继续处理下一个过滤器或者业务处理
        chain.doFilter(request, response);
    }

    // 销毁过滤器, 在容器销毁时调用, 只调用一次
    @Override
    public void destroy() {
        log.info("销毁过滤器 ...");
    }
}
