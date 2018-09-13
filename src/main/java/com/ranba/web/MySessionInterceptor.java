package com.ranba.web;

//Session拦截器器，所有需要登录的Action都将被拦截

import com.ranba.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MySessionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MySessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler){

    	try {
            User user = (User) req.getSession().getAttribute("user");

            if (null == user) {
                resp.sendRedirect("/login");
                logger.info("进入拦截器,但是没有用户信息");
                return false;
            }

            return true;

        }catch (IOException e1) {
            logger.error(e1.getMessage(), e1);
            return false;
        }
    }
}