package com.hkr.architecture.tennis.springscan.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2016/4/28
 * Time: 0:02
 * ...............................
 */
@Configuration
public class LoginContextHandlerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginContextHandlerInterceptor());
    }
}
