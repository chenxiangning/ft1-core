package com.reachauto.hkr.tennis.ft1.notscan.math;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/28 0:47
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public class BigDecimalTool {

    private BigDecimalTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    public static BigDecimalLogic is(BigDecimal value) {
        return new BigDecimalLogic(value);
    }

    public static BigDecimalLogic is(String value) {
        return new BigDecimalLogic(value);
    }

    public static BigDecimalCalculation cal(BigDecimal value) {
        return new BigDecimalCalculation(value);
    }

    public static BigDecimalCalculation cal(String value) {
        return new BigDecimalCalculation(new BigDecimal(value));
    }
}
