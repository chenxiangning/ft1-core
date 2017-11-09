package com.reachauto.hkr.tennis.ft1.guavac.cache;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 15:32
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
public class UserCacheBean {
    private String name;
    private int age;

    public UserCacheBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserCacheBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}