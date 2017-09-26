package com.reachauto.hkr.tennis.ft1.notscan.date;

import com.reachauto.hkr.tennis.ft1.DatePattern;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateToolTest {
    @Test
    public void addYear() throws Exception {
        Date date = new Date();

        System.out.println(DateTool.addYear(date, 1));
        System.out.println(DateTool.addYear(date, -1));
    }

    @Test
    public void addMonth() throws Exception {
        Date date = new Date();

        System.out.println(DateTool.addMonth(date, 1));
        System.out.println(DateTool.addMonth(date, -1));
    }

    @Test
    public void addDay() throws Exception {
    }

    @Test
    public void addWeek() throws Exception {
    }

    @Test
    public void addHour() throws Exception {
    }

    @Test
    public void addMinute() throws Exception {
    }

    @Test
    public void addSecond() throws Exception {
    }

    @Test
    public void addMillisecond() throws Exception {
    }

    @Test
    public void getYear() throws Exception {
    }

    @Test
    public void getMonth() throws Exception {
    }

    @Test
    public void getWeekOfYear() throws Exception {
    }

    @Test
    public void getDayOfYear() throws Exception {
    }

    @Test
    public void getDayOfMonth() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getDayOfMonth(date));
    }

    @Test
    public void getDayOfWeek() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getDayOfWeek(date));
    }

    @Test
    public void getHourOfYear() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getHourOfYear(date));
    }

    @Test
    public void getHourOfDay() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getHourOfDay(date));
    }

    @Test
    public void getMinute() throws Exception {
    }

    @Test
    public void getSecond() throws Exception {
    }

    @Test
    public void getSecondOfDay() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getSecondOfDay(date));
    }

    @Test
    public void getSecondOfHour() throws Exception {
        Date date = new Date();
        System.out.println(DateTool.getSecondOfHour(date));
    }

    @Test
    public void isBefore() throws Exception {
    }

    @Test
    public void isAfter() throws Exception {
    }

    @Test
    public void isInTime() throws Exception {
    }

    @Test
    public void isEquals() throws Exception {
    }

    @Test
    public void format() throws Exception {
    }

    @Test
    public void toDate() throws Exception {
        System.out.println(DateTool.toDate("2017-9-25 11:48:23", DatePattern.DATE_PATTERNS));
        System.out.println(DateTool.toDate("2017/9/25 11:48:23", DatePattern.DATE_PATTERNS));
        System.out.println(DateTool.toDate("20:10", DatePattern.DATE_PATTERNS));

        System.out.println(DateTool.format(DateTool.toDate("20:10", DatePattern.DATE_PATTERNS), DatePattern.YYYYMMDD_BAR_HHMMSS_COLON));

        System.out.println(DateTool.toDate("170925114823", DatePattern.DATE_PATTERNS));
        System.out.println(DateTool.toDate("170925114823", DatePattern.YYMMDDHHMMSS));
    }

    @Test
    public void isLeapYear() throws Exception {
    }


    @Test
    public void getNumberOfMinutesBetweenTheTwoTime() throws Exception {

        Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("20:00", "20:30"), 30);
        Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("01:00", "10:11"), 551);

        System.out.println(DateTool.getNumberOfMinutesBetweenTheTwoTime("23:00", "00:01"));
        System.out.println(DateTool.getNumberOfMinutesBetweenTheTwoTime("2017-09-25 14:15:00", "2017-09-26 14:30:00"));
        System.out.println(DateTool.getNumberOfMinutesBetweenTheTwoTime("2017-09-25 14:15:00", "2017-09-27 14:30:00"));


    }

}