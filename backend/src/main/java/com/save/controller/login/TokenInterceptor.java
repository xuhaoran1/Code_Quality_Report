package com.save.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.CancelledKeyException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class TokenInterceptor implements  HandlerInterceptor {



    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = request.getParameter("token");
        if(token!=null&&UtilsToken.checkToken(token))
        {
            request.setAttribute("userId",UtilsToken.tokenToId(token));
            System.out.println("拦截器");
            return true;
        }else {
            PrintWriter out = null ;
            JSONObject json = new JSONObject();
            json.put("isok", false);
            json.put("data","tokenFalse");
            out = response.getWriter();
            out.append(json.toString());
            return false;
        }

    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}

