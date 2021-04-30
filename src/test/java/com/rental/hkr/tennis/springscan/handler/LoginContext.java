package com.rental.hkr.tennis.springscan.handler;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-11 16:05
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
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
