package com.reachauto.hkr.tennis.springscan.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-11 15:34
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
@Component
@Slf4j
public class LoginContextHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String xAuthToken = httpServletRequest.getHeader("x-auth-token");
        LoginContextTool.set(new LoginContext(xAuthToken));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LoginContextTool.remove();
    }
}