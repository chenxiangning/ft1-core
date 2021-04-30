package com.hkr.architecture.tennis.springscan.demo;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-01-10 16:50
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
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
