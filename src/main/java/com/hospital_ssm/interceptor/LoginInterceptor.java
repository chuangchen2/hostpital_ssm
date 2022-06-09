package com.hospital_ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 得到用户请求的URI
        // 得到web应用程序的上下文路径
        // 去除上下文路径，得到剩余部分的路径
        String url = request.getHeader("referer");//之前的页面
        if (request.getSession().getAttribute("patient") == null) {
            request.getSession().setAttribute("message", "请先登录!");
            request.getSession().setAttribute("url", url);
//            System.out.println(req.getSession().getAttribute("url"));
            response.sendRedirect("login");
            return false;
        } else {
            return true;
        }
    }
}
