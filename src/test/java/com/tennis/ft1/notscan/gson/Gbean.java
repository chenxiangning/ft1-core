package com.tennis.ft1.notscan.gson;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 23:58
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public class Gbean {

    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private int code;
    @Expose
    private Double fen;
    @Expose
    private String xx;
    @Expose
    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Double getFen() {
        return fen;
    }

    public void setFen(Double fen) {
        this.fen = fen;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Gbean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
                ", fen=" + fen +
                ", xx='" + xx + '\'' +
                ", time=" + time +
                '}';
    }
}
