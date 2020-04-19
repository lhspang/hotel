package com.sen.hotel.global;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.sen.hotel.utils.JwtUtil;
import com.sen.hotel.utils.ResponseResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        if(token != null){
            Map<String, Claim> map = JwtUtil.verifyToken(token);
            if(map!=null){
                return true;
            }
        }
        ResponseResult responseResult = new ResponseResult().failure("401");
        response.getWriter().write(JSON.toJSONString(responseResult));
        response.getWriter().flush();
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
