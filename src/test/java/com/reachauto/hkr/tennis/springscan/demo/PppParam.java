package com.reachauto.hkr.tennis.springscan.demo;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-10 16:50
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */

public class PppParam {
    @NotNull
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}