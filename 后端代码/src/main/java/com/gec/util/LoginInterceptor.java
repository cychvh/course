package com.gec.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    // 请求之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 拿到前端发送的token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()){
            // token为空
            // 返回一个401响应码
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // 有token
        // 从redis 拿到value
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj == null){
            // token 失效了
            // 返回一个401响应码
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }else {
            // 已经登录
            return true;
        }
    }
}
