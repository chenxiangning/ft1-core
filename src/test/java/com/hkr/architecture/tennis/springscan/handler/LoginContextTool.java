package com.hkr.architecture.tennis.springscan.handler;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-11 16:08
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
public class LoginContextTool {

    private static final ThreadLocal<LoginContext> infoThreadLocal = new ThreadLocal<>();

    /**
     * Do not instantiate CalendarTool.
     */
    private LoginContextTool() {
        String.format("%s[%s]", "不能实例化,因为它是私有的构造方法! ", getClass().getName());
        throw new AssertionError(String.format("%s[%s]", "不能实例化,因为它是私有的构造方法! ", getClass().getName()));
    }


    public static void set(LoginContext loginInfo) {
        infoThreadLocal.set(loginInfo);
    }

    public static LoginContext get() {
        return infoThreadLocal.get();
    }

    public static void remove() {
        infoThreadLocal.remove();
    }

}
