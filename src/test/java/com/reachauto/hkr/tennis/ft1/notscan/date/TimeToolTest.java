package com.reachauto.hkr.tennis.ft1.notscan.date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TimeToolTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeToolTest.class);

    @Test
    public void getRentalTimeContainRuleTime() throws Exception {

        Assert.assertEquals(TimeTool.getRentalTimeContainRuleTime("2017-09-25 15:48", "2017-09-26 18:48:29",
                "10:00", "14:00", 10), 240);

        Assert.assertEquals(TimeTool.getRentalTimeContainRuleTime("2017-09-25 15:48", "2017-09-26 18:48:29",
                "22:00", "01:00", 10), 180);
        Assert.assertEquals(TimeTool.getRentalTimeContainRuleTime("2017-09-25 10:01:00", "2017-09-26 18:48:29",
                "10:00", "12:00", 10), 229);


    }

    @Test
    public void smallTimeSmallDate() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "08:00";
        String happyTimeEnd = "09:00";

        String rentalStart = "2016-05-02 08:30:00";
        String rentalEnd = "2016-05-03 10:00:00";

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 30 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void smallTimeBigDate() {
        LOGGER.debug("前小后大_前大后小2");

        String happyTimeStart = "08:00";
        String happyTimeEnd = "09:00";
        String rentalStart = "2016-05-02 10:30:00";
        String rentalEnd = "2016-05-03 08:30:00";


        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 30;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bigTimeSmallDate() {
        LOGGER.debug("前大后小_前小后大3");

        String happyTimeStart = "23:00";
        String happyTimeEnd = "01:00";

        String rentalStart = "2016-05-02 00:30:00";
        String rentalEnd = "2016-05-03 09:00:00";


        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 30 + 60 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bigTimeBigDate() {
        LOGGER.debug("前大后小_前大后小4");

        String happyTimeStart = "23:00";
        String happyTimeEnd = "07:00";

        String rentalStart = "2016-05-02 08:30:00";
        String rentalEnd = "2016-05-03 06:00:00";


        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 420;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bigTimeSameDate() {
        LOGGER.debug("前大后小_等于5");

        String happyTimeStart = "23:00";
        String happyTimeEnd = "07:00";

        String rentalStart = "2016-05-02 08:30:00";
        String rentalEnd = "2016-05-03 08:30:00";


        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 480;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void smallTimeSameDate() {
        LOGGER.debug("前小后大_等于6");

        String happyTimeStart = "18:00";
        String happyTimeEnd = "20:00";

        String rentalStart = "2016-05-02 19:30:00";
        String rentalEnd = "2016-05-03 19:30:00";


        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 120;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void smallTimeSameDateCheck() {
        LOGGER.debug("前小后大_等于7");

        String happyTimeStart = "15:01";
        String happyTimeEnd = "17:31";

        String rentalStart = "2016-06-30 17:28:20";
        String rentalEnd = "2016-06-30 17:29:19";
        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 1;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bug_1() throws Exception {
        LOGGER.debug("bug_1");

        String happyTimeStart = "15:00";
        String happyTimeEnd = "15:30";

        String rentalStart = "2016-07-11 15:10:13";
        String rentalEnd = "2016-07-11 15:55:37";
        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 5);
        long desiredValue = 15;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bug_2() throws Exception {
        LOGGER.debug("bug_1");

        String happyTimeStart = "13:00";
        String happyTimeEnd = "13:30";

        String rentalStart = "2016-07-12 13:23:20";
        String rentalEnd = "2016-07-12 13:23:25";
        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 1;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bug_3() throws Exception {
        LOGGER.debug("bug_1");

        String happyTimeStart = "13:00";
        String happyTimeEnd = "13:30";

        String rentalStart = "2016-07-12 18:23:20";
        String rentalEnd = "2016-07-12 18:23:25";
//
        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 0;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }

    @Test
    public void bug_4() throws Exception {
        LOGGER.debug("bug_1");

        String happyTimeStart = "18:00";
        String happyTimeEnd = "19:30";

        String rentalStart = "2016-07-12 18:23:20";
        String rentalEnd = "2016-07-12 18:23:20";
//
        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
        long desiredValue = 1;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);
        LOGGER.debug("desiredValue:" + desiredValue);

        Assert.assertEquals(desiredValue, innerTime);

    }


    @Test
    public void testCheckCurrentDateBetween() {
        LOGGER.debug("验证当前时间是否包含在两个日期之间");

        Date before = DateTool.toDate("2016-07-04 11:10:00", DatePattern.YYYYMMDD_BAR_HHMMSS_COLON);
        Date after = DateTool.toDate("2016-07-04 11:50:60", DatePattern.YYYYMMDD_BAR_HHMMSS_COLON);

        boolean c = DateTool.isInTime(DateTool.toDate("2016-07-04 11:33:00", DatePattern.YYYYMMDD_BAR_HHMMSS_COLON), before, after);

        Assert.assertEquals(c, true);
        LOGGER.debug("result:{}", c);

    }

    @Test
    public void testCheckCurrentDateBetween2() throws Exception {
        LOGGER.debug("验证当前时间是否包含在两个日期之间");

        boolean c = DateTool.checkDateToBetween("09:59", "10:00", "15:01");
        Assert.assertEquals(c, false);
        boolean d = DateTool.checkDateToBetween("10:00", "10:00", "15:01");
        Assert.assertEquals(d, true);
        boolean e = DateTool.checkDateToBetween("15:01", "10:00", "15:01");
        Assert.assertEquals(e, false);


        boolean q = DateTool.checkDateToBetween("2017-09-26 10:06:24", "09:06:24", "11:06:24");
        Assert.assertEquals(q, true);


    }


    @Test
    public void smallTimeSmallDatex() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "14:00";
        String happyTimeEnd = "16:00";

        String rentalStart = "2017-04-14 14:00:00";
        String rentalEnd = "2017-04-15 00:00:00";
//

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 0);
//        long desiredValue = 30 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);

        Assert.assertEquals(120, innerTime);

    }

    @Test
    public void smallTimeSmallDatex2() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "14:00";
        String happyTimeEnd = "16:00";

        String rentalStart = "2017-04-14 14:00:00";
        String rentalEnd = "2017-04-14 18:00:01";
//

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 30);
//        long desiredValue = 30 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);

        Assert.assertEquals(90, innerTime);

    }

    @Test
    public void smallTimeSmallDatex3() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "08:00";
        String happyTimeEnd = "16:00";

        String rentalStart = "2017-04-14 15:10:00";
        String rentalEnd = "2017-04-14 16:00:00";
//

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 30);
//        long desiredValue = 30 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);

//        Assert.assertEquals(90, innerTime);

    }


    @Test
    public void smallTimeSmallDatex4() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "21:00";
        String happyTimeEnd = "05:00";

        String rentalStart = "2017-04-14 23:30:00";
        String rentalEnd = "2017-04-15 05:00:00";
//

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 30);
//        long desiredValue = 30 + 60;

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);

        Assert.assertEquals(300, innerTime);

    }

    @Test
    public void smallTimeSmallDatex5() {
        LOGGER.debug("前小后大_前小后大1");

        String happyTimeStart = "10:00";
        String happyTimeEnd = "11:00";

        String rentalStart = "2017-04-14 10:29:00";
        String rentalEnd = "2017-04-14 11:00:00";
//

        long innerTime = TimeTool.getRentalTimeContainRuleTime(rentalStart, rentalEnd, happyTimeStart, happyTimeEnd, 30);

        LOGGER.debug("优惠:" + happyTimeStart + " " + happyTimeEnd);
        LOGGER.debug("租车:" + rentalStart + " " + rentalEnd);
        LOGGER.debug("包含优惠时间:" + innerTime);

        Assert.assertEquals(1, innerTime);

    }

}