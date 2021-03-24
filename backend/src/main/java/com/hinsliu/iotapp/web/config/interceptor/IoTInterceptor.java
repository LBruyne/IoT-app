package com.hinsliu.iotapp.web.config.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: Implementation for Interceptor to pre handle the request.
 * @Function: An interceptor，only when Func:preHandle returns true，
 *            can the Func:postHandle and Func:afterCompletion be executed.
 * @author: liuxuanming
 * @date: 2021/03/24 6:32 下午
 */
public class IoTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession s = request.getSession();
        // cannot find user info from Session.
        if(s.getAttribute("user") == null){
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
