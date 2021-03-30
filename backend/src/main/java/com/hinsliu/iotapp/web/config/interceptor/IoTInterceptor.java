package com.hinsliu.iotapp.web.config.interceptor;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hinsliu.iotapp.biz.utils.redis.RedisUtil;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.web.annotation.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.tree.ExpandVetoException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @Description: Implementation for Interceptor to pre handle the request.
 * @Function: An interceptor，only when Func:preHandle returns true，
 *            can the Func:postHandle and Func:afterCompletion be executed.
 * @author: liuxuanming
 * @date: 2021/03/24 6:32 下午
 */
@Configuration
public class IoTInterceptor implements HandlerInterceptor {

    private static final String tokenHeader = "Authorization";

    private static Logger logger = LoggerFactory.getLogger(IoTInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        // With AuthToken annotation, we need a validation
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            // get token
            String token = request.getHeader(tokenHeader);

            // validate the token
            RedisUtil redis = RedisUtil.getInstance();
            if (token != null && token.length() != 0) {
                String userName = redis.get(token);
                // if the token is existed.
                // update the token
                if(userName != null && !userName.equals("")) {
                    redis.set(token, userName);
                    return true;
                }
            }

            try {
                response.sendRedirect("/app/callback");
                return false;
            }
            catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }

        // No AuthToken Annotation.
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
