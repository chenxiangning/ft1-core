package com.reachauto.hkr.tennis.ft1.notscan.date;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-09-25 15:28
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 租赁车辆订单中的时间与优惠时间段的对比获取优惠时间计算
 */
public final class TimeTool {

    private static final String ZERO_OCLOCK_WITH_SPACE = " 00:00";
    private static final String TWENTY_TWO_OCLOCK_WITH_SPACE = " 24:00";

    private static final String ZERO_OCLOCK = "00:00";
    private static final String TWENTY_TWO_OCLOCK = "24:00";

    private TimeTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }


    /**
     * 获取租车时间中包含的波峰/波谷占的时间 单位 min
     *
     * @param rentalBeforeTime yyyy-MM-dd HH:mm:ss 租车开始时间
     * @param rentalAfterTime  yyyy-MM-dd HH:mm:ss  租车结束时间
     * @param ruleBeforeTime   HH:mm          优惠开始时间  24小时制度
     * @param ruleAfterTime    HH:mm           优惠结束时间
     * @return
     */
    public static long getRentalTimeContainRuleTime(String rentalBeforeTime, String rentalAfterTime,
                                                    String ruleBeforeTime, String ruleAfterTime, int initTime) {

        long yhsj = getHappyHour(rentalBeforeTime, rentalAfterTime, ruleBeforeTime, ruleAfterTime, initTime);
        return yhsj < 0 ? 0 : yhsj;
    }

    /**
     * 获取租车时间中包含的波峰/波谷占的时间 单位 min
     *
     * @param rentalBeforeTime yyyy-MM-dd HH:mm:ss 租车开始时间
     * @param rentalAfterTime  yyyy-MM-dd HH:mm:ss  租车结束时间
     * @param ruleBeforeTime   HH:mm          优惠开始时间  24小时制度
     * @param ruleAfterTime    HH:mm           优惠结束时间
     * @return
     */
    public static long getRentalTimeContainRuleTime(Date rentalBeforeTime, Date rentalAfterTime,
                                                    String ruleBeforeTime, String ruleAfterTime, int initTime) {
        return getRentalTimeContainRuleTime(
                DateTool.format(rentalBeforeTime, DatePattern.YYYYMMDD_BAR_HHMMSS_COLON),
                DateTool.format(rentalAfterTime, DatePattern.YYYYMMDD_BAR_HHMMSS_COLON),
                ruleBeforeTime, ruleAfterTime, initTime);
    }


    /**
     * 包含中的时间计算
     *
     * @param rentalBeforeTime
     * @param rentalAfterTime
     * @param ruleBeforeTime
     * @param ruleAfterTime
     * @return
     */
    private static long containRuleTime(String rentalBeforeTime, String rentalAfterTime, String ruleBeforeTime, String ruleAfterTime) {
        // 1.计算总时间段包含总时间 min
        long zong = DateTool.getNumberOfMinutesBetweenTheTwoTime(rentalBeforeTime, rentalAfterTime);
        // 2.计算优惠单个时间段包含的总时间 min
        long yhTime = DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime, ruleAfterTime);
        // 3.计算总时间段多少天
        int tian = new BigDecimal(zong).divide(new BigDecimal(TimeInterval.MINUTE_PER_DAY), 0, BigDecimal.ROUND_UP).intValue();

        CheckYhTime checkYhTime = new CheckYhTime(rentalBeforeTime, rentalAfterTime, ruleBeforeTime, ruleAfterTime).invoke();

        long jsYhTime = DateTool.getNumberOfMinutesBetweenTheTwoTime(checkYhTime.getBeforeYh(), checkYhTime.getAfterYh());

        return (tian - 1) * yhTime + jsYhTime;
    }


    private static long getHappyHour(String rentalBeforeTime, String rentalAfterTime, String ruleBeforeTime, String ruleAfterTime, int initTime) {
        CheckYhTime checkYhTime = new CheckYhTime(rentalBeforeTime, rentalAfterTime, ruleBeforeTime, ruleAfterTime, initTime);
        int yhBefore = checkYhTime.getYhBefore();
        int yhAfter = checkYhTime.getYhAfter();
        int zBefore = checkYhTime.getzBefore();
        int zAfter = checkYhTime.getzAfter();

        // 09:00 10:00 2000-01-01 10:00 - 2000-01-03 11:00 前小后大 前小后大
        if (yhBefore < yhAfter && zBefore < zAfter) {
            return containRuleTime(rentalBeforeTime, checkYhTime.getRentalAfterTime(), ruleBeforeTime, ruleAfterTime) - checkYhTime.getHssj();
        }

        // 09:00 10:00 2000-01-01 10:00 - 2000-01-03 08:00 前小后大 前大后小
        if (yhBefore < yhAfter && zBefore >= zAfter) {

            long a = containRuleTime(rentalBeforeTime, rentalBeforeTime.substring(0, 10) + TWENTY_TWO_OCLOCK_WITH_SPACE, ruleBeforeTime, ruleAfterTime);

            long b = containRuleTime(DateTool.format(DateTool.addDay(rentalBeforeTime, 1), DatePattern.YYYYMMDD_BAR) + ZERO_OCLOCK_WITH_SPACE,
                    checkYhTime.getRentalAfterTime(), ruleBeforeTime, ruleAfterTime);
            if (b < 0) {
                b = 0;
            }
            return a + b - checkYhTime.getHssj();
        }

        // 23:00 01:00 2000-01-01 10:00 - 2000-01-03 11:00  前大后小 前小后大
        if (yhBefore > yhAfter && zBefore < zAfter) {

            long a = containRuleTime(rentalBeforeTime, checkYhTime.getRentalAfterTime(), ruleBeforeTime, TWENTY_TWO_OCLOCK);

            long b = containRuleTime(rentalBeforeTime, checkYhTime.getRentalAfterTime(), ZERO_OCLOCK, ruleAfterTime);

            return a + b - checkYhTime.getHssj();
        }

        // 23:00 09:00 2000-01-01 10:00 - 2000-01-03 08:00 前大后小 前大后小
        if (yhBefore > yhAfter && zBefore >= zAfter) {

            long a = containRuleTime(rentalBeforeTime, rentalBeforeTime.substring(0, 10) + TWENTY_TWO_OCLOCK_WITH_SPACE, ruleBeforeTime, TWENTY_TWO_OCLOCK);
            long b = containRuleTime(rentalBeforeTime, rentalBeforeTime.substring(0, 10) + TWENTY_TWO_OCLOCK_WITH_SPACE, ZERO_OCLOCK, ruleAfterTime);

            long c = containRuleTime(DateTool.format(DateTool.addDay(rentalBeforeTime, 1), DatePattern.YYYYMMDD_BAR) + ZERO_OCLOCK_WITH_SPACE,
                    checkYhTime.getRentalAfterTime(), ruleBeforeTime, TWENTY_TWO_OCLOCK);

            long d = containRuleTime(DateTool.format(DateTool.addDay(rentalBeforeTime, 1), DatePattern.YYYYMMDD_BAR) + ZERO_OCLOCK_WITH_SPACE,
                    checkYhTime.getRentalAfterTime(), ZERO_OCLOCK, ruleAfterTime);

            return a + b + c + d - checkYhTime.getHssj();
        }

        return 0;
    }


    private static class CheckYhTime {
        private String rentalBeforeTime;
        private String rentalAfterTime;
        private String ruleBeforeTime;
        private String ruleAfterTime;
        private int yhBefore;
        private int yhAfter;
        private int zBefore;
        private int zAfter;
        private String beforeYh = ZERO_OCLOCK;
        private String afterYh = ZERO_OCLOCK;
        // 核算时间租车时长开始于优惠时间时收initTime不需要计算进入优惠时间段里
        private int initTime = 0;
        private int hssj = 0;

        public CheckYhTime(String rentalBeforeTime, String rentalAfterTime, String ruleBeforeTime, String ruleAfterTime) {

            this.rentalBeforeTime = rentalBeforeTime;
            this.rentalAfterTime = rentalAfterTime;
            this.ruleBeforeTime = ruleBeforeTime;
            this.ruleAfterTime = ruleAfterTime;
        }

        public CheckYhTime(String rentalBeforeTime, String rentalAfterTime, String ruleBeforeTime, String ruleAfterTime, int initTime) {

            this.rentalBeforeTime = rentalBeforeTime;
            this.rentalAfterTime = rentalAfterTime;
            this.ruleBeforeTime = ruleBeforeTime;
            this.ruleAfterTime = ruleAfterTime;
            this.initTime = initTime;

            // 对比作用
            if (isSameMinuteOfTheDay()) {
                this.rentalAfterTime = DateTool.format(DateTool.addMinute(rentalAfterTime, 1), DatePattern.YYYYMMDD_BAR_HHMMSS_COLON);
            }

            invoke();

            // 还原
            this.rentalAfterTime = rentalAfterTime;


            if (isSameMinuteSecondOfTheDay()) {
                // 同一秒钟+1秒 计算
                this.rentalAfterTime = DateTool.format(DateTool.addSecond(rentalAfterTime, 1), DatePattern.YYYYMMDD_BAR_HHMMSS_COLON);
            }


        }

        public int getHssj() {
            return hssj;
        }

        /**
         * 验证开始结束时间是否时当天的同一分钟
         *
         * @return
         */
        public boolean isSameMinuteOfTheDay() {
            return rentalBeforeTime.substring(0, 16).equals(rentalAfterTime.substring(0, 16));
        }

        /**
         * 验证开始结束时间是否时当天的同一秒
         *
         * @return
         */
        public boolean isSameMinuteSecondOfTheDay() {
            return rentalBeforeTime.equals(rentalAfterTime);
        }

        public String getRentalBeforeTime() {
            return rentalBeforeTime;
        }

        public String getRentalAfterTime() {
            return rentalAfterTime;
        }

        public String getRuleBeforeTime() {
            return ruleBeforeTime;
        }

        public String getRuleAfterTime() {
            return ruleAfterTime;
        }

        public int getInitTime() {
            return initTime;
        }

        public String getAfterYh() {
            return afterYh;
        }

        public String getBeforeYh() {
            return beforeYh;
        }

        public int getYhBefore() {
            return yhBefore;
        }

        public int getYhAfter() {
            return yhAfter;
        }

        public int getzBefore() {
            return zBefore;
        }

        public int getzAfter() {
            return zAfter;
        }

        public CheckYhTime invoke() {
            yhBefore = Integer.parseInt(ruleBeforeTime.substring(0, 2) + ruleBeforeTime.substring(3, 5));
            yhAfter = Integer.parseInt(ruleAfterTime.substring(0, 2) + ruleAfterTime.substring(3, 5));
            zBefore = Integer.parseInt(rentalBeforeTime.substring(11, 13) + rentalBeforeTime.substring(14, 16));
            zAfter = Integer.parseInt(rentalAfterTime.substring(11, 13) + rentalAfterTime.substring(14, 16));

            // 4.计算单个时间段是否在总时间跨度中
            // 优惠时间段开始包含于总时间
            if (yhBefore >= zBefore && yhBefore <= zAfter) {
                beforeYh = ruleBeforeTime.substring(0, 5) + ":00";
            }

            // 优惠时间段开始不包含于总时间,优惠结束时间段包含于
            if (yhBefore < zBefore && yhAfter >= zBefore) {
                beforeYh = rentalBeforeTime.substring(11);
            }

            // 优惠时间段结束包含于总时间
            if (yhAfter >= zBefore && yhAfter <= zAfter) {
                afterYh = ruleAfterTime.substring(0, 5) + ":00"; // 优惠结束时间
            }

            // 优惠时间段结束不包含于总时间,优惠开始时间段包含于
            if (yhAfter > zAfter && yhBefore <= zAfter) {
                afterYh = rentalAfterTime.substring(11); // 优惠结束时间
            }

            // initTime 起步时间到优惠时间之间 计算 优惠时间
            if (DateTool.getNumberOfMinutesBetweenTheTwoTime(rentalBeforeTime.substring(11), ruleBeforeTime.concat(":00")) >= 0) {
                hssj = initTime - DateTool.getNumberOfMinutesBetweenTheTwoTime(rentalBeforeTime.substring(11), ruleBeforeTime.concat(":00"));
                if (hssj < 0) {
                    hssj = 0;
                }
            }

            if (DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime.concat(":00"), rentalBeforeTime.substring(11)) >= 0) {

                long jsYhTime = DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime, ruleAfterTime);
                if (DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime.concat(":00"), rentalBeforeTime.substring(11)) < jsYhTime) {
                    hssj = initTime;
                }
            }

            return this;
        }
    }
}