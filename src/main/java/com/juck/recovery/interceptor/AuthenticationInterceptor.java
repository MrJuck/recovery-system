package com.juck.recovery.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.juck.recovery.annotation.PassLogin;
import com.juck.recovery.exception.ErrorInfo;
import com.juck.recovery.utils.MemoryCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.info("=====handlerinterceptor ======");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");


        String token = request.getHeader("token");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(PassLogin.class)) {
            LOGGER.info("request: {}, no need to login", request.getRequestURL());
            return true;
        }

        LOGGER.info("request: {}, login first please", request.getRequestURL());
        if (StringUtils.isBlank(token)) {
            throw new ErrorInfo("000000", "login first");
        }

        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ErrorInfo("000000", "login first", e.getCause());
        }

        Object cacheObj = MemoryCache.get(userId + "#PWD");
        String pwd = "";
        if (cacheObj instanceof String) {
            pwd = (String) cacheObj;
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(pwd)).build();
        try {
            jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new ErrorInfo("000000", "login first", e.getCause());
        }

        return true;
    }
}
