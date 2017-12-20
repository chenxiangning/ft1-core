package com.reachauto.hkr.tennis.notscan.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/28 0:47
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public class BigDecimalCalculation {
    private final BigDecimal amount;

    public BigDecimalCalculation(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimalCalculation(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public BigDecimalCalculation plus(BigDecimal value, int scale) {
        return new BigDecimalCalculation(this.amount.add(value).setScale(scale, RoundingMode.FLOOR));
    }

    public BigDecimalCalculation plus(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.add(value));
    }

    public BigDecimalCalculation plus(String value) {
        return plus(new BigDecimal(value));
    }


    public BigDecimalCalculation minus(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.subtract(value));
    }

    public BigDecimalCalculation minus(BigDecimal value, int scale) {
        return new BigDecimalCalculation(this.amount.subtract(value).setScale(scale, RoundingMode.FLOOR));
    }

    public BigDecimalCalculation minus(String value) {
        return minus(new BigDecimal(value));
    }


    public BigDecimalCalculation mul(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.multiply(value));
    }

    public BigDecimalCalculation mul(String value) {
        return mul(new BigDecimal(value));
    }

    public BigDecimalCalculation div(BigDecimal value) {
        return new BigDecimalCalculation(this.amount.divide(value, RoundingMode.FLOOR));
    }

    public BigDecimalCalculation div(String value) {
        return div(new BigDecimal(value));
    }

    public BigDecimalCalculation div(BigDecimal value, int scale) {
        return new BigDecimalCalculation(this.amount.divide(value, scale, RoundingMode.FLOOR));
    }

    public BigDecimalCalculation div(String value, int scale) {
        return div(new BigDecimal(value), scale);
    }

    public BigDecimal result() {
        return this.amount;
    }
}
