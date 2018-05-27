package com.aiyundong;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception{
        Object obj = req.getSession().getAttribute("user_name");
        if (obj == null || !(obj instanceof String)){
            resp.sendRedirect("/login");
            return false;
        }

        return false;
    }
}