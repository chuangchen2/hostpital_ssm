package com.hospital_ssm.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.JWTUtil;
import com.hospital_ssm.util.R;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JWTUtil.USER_LOGIN_TOKEN);
        R r = new R();
        //token不存在
        if (token == null || token.equals("")) {
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setMessage("令牌不存在，请重新登录！");
            String json = new ObjectMapper().writeValueAsString(r);
            response.getWriter().print(json);
            return false;
        }
        //验证token
        String sub = JWTUtil.validateToken(token);
        if (sub == null || sub.equals("")) {
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setMessage("token验证失败");
            String json = new ObjectMapper().writeValueAsString(r);
            response.getWriter().print(json);
            return false;
        }
        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtil.isNeedUpdate(token)){
            String newToken = JWTUtil.createToken(sub);
            response.setHeader(JWTUtil.USER_LOGIN_TOKEN, newToken);
        }
        return true;
    }
}
