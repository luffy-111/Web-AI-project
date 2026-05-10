package com.itluffy.filter;

import com.itluffy.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {

    /*
     * 过滤器
     * 验证令牌
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //1. 获取请求路径
        String path = req.getRequestURI();  // 去除协议、域名、端口号，保留路径

        //2. 判断是否为登录请求, 登录请求放行
        if (path.contains("/login")) {
            log.info("登录请求, 放行");
            chain.doFilter(request, response);
            return;
        }

        //3. 获取请求头中的token
        String token = req.getHeader("token");

        //4. 判断token是否存在，如果存在则放行，如果不存在则返回401错误
        if (token == null || token.isEmpty()) {
            log.info("token不存在, 返回401");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5. 验证令牌是否有效, 如果有效则放行，如果无效则返回401错误
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("token非法, 返回401");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6. 放行
        log.info("令牌有效, 放行");
        chain.doFilter(request, response);
    }
}
