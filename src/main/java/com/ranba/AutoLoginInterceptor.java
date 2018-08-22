package com.ranba;

import com.ranba.model.User;
import com.ranba.util.Cookies;
import com.ranba.util.RanbaSequenceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception{
        // 判断读取cookie内容
        User user = (User) req.getSession().getAttribute("user");
        String cookvalue = Cookies.getCookieByName(req, "user_key");

        //添加购物车数据
        String cookie_cart = Cookies.getCookieByName(req, "cart_num");
        if( StringUtils.isBlank(cookie_cart) ) {
            cookie_cart = RanbaSequenceUtil.genCartNum();
            Cookies.addCookie(req, resp, "cart_num", cookie_cart, 30);
            Cookies.addCookie(req, resp, "cart_count", "0", 30);
        }

        //添加注册次数标记
        String reg_flag = Cookies.getCookieByName(req, "reg_flag");
        if( StringUtils.isBlank(reg_flag) ) {
            Cookies.addCookie(req, resp, "reg_flag", "0", 30);
        }

        return true;
    }
}