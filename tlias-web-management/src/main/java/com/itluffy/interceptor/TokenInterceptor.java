package com.itluffy.interceptor;

import com.itluffy.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 令牌拦截器，用于拦截请求并验证令牌
 * */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 获取请求路径
        //String path = request.getRequestURI();  // 去除协议、域名、端口号，保留路径

        //2. 判断是否为登录请求, 登录请求放行
        /*if (path.contains("/login")) {
            log.info("登录请求, 放行");
            return true;
        }*/

        //3. 获取请求头中的token
        String token = request.getHeader("token");

        //4. 判断token是否存在，如果存在则放行，如果不存在则返回401错误
        if (token == null || token.isEmpty()) {
            log.info("token不存在, 返回401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5. 验证令牌是否有效, 如果有效则放行，如果无效则返回401错误
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("token非法, 返回401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6. 放行
        log.info("令牌有效, 放行");
        return true;
    }

}
