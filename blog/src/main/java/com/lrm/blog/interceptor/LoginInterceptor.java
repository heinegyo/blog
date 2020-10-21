package com.lrm.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {//確認session裡面的user裡面是否為空 為空代表沒有登入
            response.sendRedirect("/admin");//重導向到登入頁面
            return false;
        }
        return true;//繼續往下執行
    }
}
//perHandle在請求未到達目的時先處理
