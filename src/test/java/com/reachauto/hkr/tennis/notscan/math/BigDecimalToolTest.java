package com.reachauto.hkr.tennis.notscan.math;

import org.junit.Test;

import java.math.BigDecimal;

import static com.reachauto.hkr.tennis.notscan.math.BigDecimalTool.cal;
import static com.reachauto.hkr.tennis.notscan.math.BigDecimalTool.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BigDecimalToolTest {
    BigDecimal one = new BigDecimal("1.4201");
    BigDecimal two = new BigDecimal("3.521");
    BigDecimal three = new BigDecimal("6.521");
    BigDecimal four = new BigDecimal("6.521");
    BigDecimal five = null;

    @Test
    public void testTwoBigdecimalsEquality() {
        assertTrue(is(three).eq(four));
        assertTrue(is(one).eq(one));
        assertTrue(!is(one).eq(two));
        assertTrue(is(three).eq("6.521"));
        assertTrue(is("1").eq("1"));
    }

    @Test
    public void testABigdecimalIsGratherThanTheOther() {
        assertTrue(!is(three).gt(four));
        assertTrue(!is(two).gt(two));
        assertTrue(is(two).gt(one));
        assertTrue(is(three).gt(two));
    }

    @Test
    public void testABigdecimalIsGratherThanEqualsToTheOther() {
        assertTrue(is(three).gteq(four));
        assertTrue(is(two).gteq(two));
        assertTrue(is(two).gteq(one));
        assertTrue(!is(two).gteq(three));
    }

    @Test
    public void testABigdecimalIsLessThanTheOther() {
        assertTrue(!is(three).lt(four));
        assertTrue(!is(two).lt(two));
        assertTrue(is(one).lt(two));
        assertTrue(is(two).lt(three));
    }

    @Test
    public void testABigdecimalIsLessThanEqualsToTheOther() {
        assertTrue(is(three).lteq(four));
        assertTrue(is(two).lteq(two));
        assertTrue(is(one).lteq(two));
        assertTrue(!is(three).lteq(two));
    }

    @Test
    public void testZeroIsZero() {
        assertTrue(is("0").isZero());
    }

    @Test
    public void testNullOrZero() {
        assertTrue(is(five).isNullOrZero());
        assertTrue(is("0").isNullOrZero());
        assertFalse(is("1").isNullOrZero());
    }

    @Test
    public void calk() {
        System.out.println(cal(new BigDecimal("12")).plus(new BigDecimal(12)).result());
        System.out.println(cal(new BigDecimal("12.2222")).plus(new BigDecimal("12.0193094")).result());
        System.out.println(cal(new BigDecimal("12.2222")).plus(new BigDecimal("12.0193094"),2).result());
        System.out.println(cal(new BigDecimal("8")).div(new BigDecimal("9")).result());
        System.out.println(cal(new BigDecimal("8")).div(new BigDecimal("9"),1).result());
        System.out.println(cal(new BigDecimal("8")).div(new BigDecimal("9"),2).result());
        System.out.println(cal(new BigDecimal("8")).div(new BigDecimal("9"),3).result());
        System.out.println(cal(new BigDecimal("8")).div(new BigDecimal("9"),4).result());

        System.out.println(is("123").gt("123"));
        System.out.println(is("123").gteq("123"));
    }
}