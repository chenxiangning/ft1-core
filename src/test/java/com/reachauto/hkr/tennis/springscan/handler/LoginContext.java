package com.reachauto.hkr.tennis.springscan.handler;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-11 16:05
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
public class LoginContext {
    public String token;

    public LoginContext(String xAuthToken) {
        this.token = xAuthToken;
    }

    public LoginContext() {
    }

    @Override
    public String toString() {
        return "LoginContext{" +
                "token='" + token + '\'' +
                '}';
    }
}