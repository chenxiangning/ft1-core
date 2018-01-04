package com.reachauto.hkr.tennis.notscan.date;

import com.reachauto.hkr.tennis.Slf4jTool;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.reachauto.hkr.tennis.notscan.date.DatePattern.YYYYMMDD_BAR_HHMMSS_COLON;
import static com.reachauto.hkr.tennis.notscan.date.DatePattern.YYYYMMDD_BAR_HHMM_COLON;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-09-25 15:28
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 租赁车辆订单中的时间与优惠时间段的对比获取优惠时间计算
 *
 * @author cxn
 */
public final class TimeTool {

    private static final String ZERO_OCLOCK_WITH_SPACE = " 00:00";
    private static final String TWENTY_TWO_OCLOCK_WITH_SPACE = " 24:00";

    private static final String ZERO_OCLOCK = "00:00";
    private static final String ZERO2_OCLOCK = ":00";
    private static final String ZERO3_OCLOCK = "00:00:00";
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
     * @param initTime         初始起步时间 数字
     * @return 时间
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
     * @param initTime         初始起步时间 数字
     * @return 时间
     */
    public static long getRentalTimeContainRuleTime(Date rentalBeforeTime, Date rentalAfterTime,
                                                    String ruleBeforeTime, String ruleAfterTime, int initTime) {
        return getRentalTimeContainRuleTime(
                DateTool.format(rentalBeforeTime, YYYYMMDD_BAR_HHMMSS_COLON),
                DateTool.format(rentalAfterTime, YYYYMMDD_BAR_HHMMSS_COLON),
                ruleBeforeTime, ruleAfterTime, initTime);
    }


    /**
     * 获取两个时间之间的分钟数,第一个时间舍弃秒,或者说秒=00 <code>before</code> 使用<b>一个或者多个</b>不同的 <code>after</code>
     * <p>
     * <h3>示例:</h3>
     * <p>
     * <blockquote>
     * <p>
     * <pre class="code">
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("23:00", "00:01"),61);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("20:00", "20:30"),30);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("01:00", "10:11"),551);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("2017-09-25 14:15:00", "2017-09-26 14:30:00"),1455);
     * </pre>
     * <p>
     * </blockquote>
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 获取两个时间之间的分钟数
     */
    public static int getNumberOfMinutesBetweenTheTwoTimeAlsoBeforeTimeAbandonSS(String before, String after) {
        return DateTool.getNumberOfMinutesBetweenTheTwoTime(DateTool.format(DateTool.toDate(before, DatePattern.DATE_PATTERNS), YYYYMMDD_BAR_HHMM_COLON), after);
    }


    /**
     * 包含中的时间计算
     *
     * @param rentalBeforeTime
     * @param rentalAfterTime
     * @param ruleBeforeTime
     * @param ruleAfterTime
     * @return 包含中的时间
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

    /**
     * 将yyyy-MM-dd HH:mm:ss的时间类型的字符串中秒钟清零为00
     *
     * @param yyyyMMddHHmmss yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String convertSecondsClearZero(String yyyyMMddHHmmss) {

        try {
            DateUtils.parseDate(yyyyMMddHHmmss, YYYYMMDD_BAR_HHMMSS_COLON);
            return yyyyMMddHHmmss.substring(0, yyyyMMddHHmmss.length() - 3).concat(ZERO2_OCLOCK);
        } catch (ParseException e) {
            String pattern = "parse dateString [{}] use patterns:[{}] to date exception,message:[{}]";
            throw new IllegalArgumentException(Slf4jTool.format(pattern, yyyyMMddHHmmss, YYYYMMDD_BAR_HHMMSS_COLON, e.getMessage()), e);
        }
    }

    /**
     *
     * @param date
     * @return 转换为yyyy-MM-dd HH:mm:ss的时间类型的字符串中秒钟清零为00
     */
    public static String convertSecondsClearZero(long date) {
        return DateTool.format(new Date(date), YYYYMMDD_BAR_HHMM_COLON).concat(ZERO2_OCLOCK);
    }

    /**
     * 时间戳秒钟去除
     * @param date
     * @return
     */
    public static Date convertSecondsClearZeroToDate(long date) {
        return DateTool.toDate(convertSecondsClearZero(date),YYYYMMDD_BAR_HHMMSS_COLON);
    }

    /**
     * @param orderBeforeTime
     * @param orderAfterTime
     * @param ruleBefore24Time
     * @param ruleAfter24Time
     * @param initTime
     * @return
     */
    public static List<TimeSpan> figureOutTimeSpan(String orderBeforeTime, String orderAfterTime,
                                                   String ruleBefore24Time, String ruleAfter24Time, int initTime) {

        List<TimeSpan> timeSpans = new ArrayList<>();

        Date orderBeforeTimeToDate = DateTool.toDate(orderBeforeTime, YYYYMMDD_BAR_HHMMSS_COLON);

        Date orderBeforeTimeToDateAddInitTime = DateTool.addMinute(orderBeforeTimeToDate, initTime);

        String orderBeforeTimeAddInitTimeHHMM = DateTool.format(orderBeforeTimeToDateAddInitTime, DatePattern.HHMM_COLON);
        // 开始时间段
        if (DateTool.checkDateToBetween(orderBeforeTimeAddInitTimeHHMM, ruleBefore24Time, ruleAfter24Time)) {
            // 开始时间到规则结束时间
            TimeSpan timeSpan = new TimeSpan();
            timeSpan.setSort(1);
            timeSpan.setStime(DateTool.format(orderBeforeTimeToDateAddInitTime, YYYYMMDD_BAR_HHMMSS_COLON));

            long beforeTime = DateTool.toDate(ruleBefore24Time, DatePattern.DATE_PATTERNS).getTime();
            long afterTime = DateTool.toDate(ruleAfter24Time, DatePattern.DATE_PATTERNS).getTime();
            long currentTime = DateTool.toDate(orderBeforeTimeAddInitTimeHHMM, DatePattern.DATE_PATTERNS).getTime();
            // 11 12 结束时间当天
            if (beforeTime < afterTime) {
                timeSpan.setEtime(DateTool.format(orderBeforeTimeToDateAddInitTime, DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }
            // 22 03 次日
            if (beforeTime > afterTime) {
                Date isToMorrow = DateTool.addDay(orderBeforeTimeToDate, 0);
                if (currentTime >= beforeTime && currentTime > afterTime) {
                    isToMorrow = DateTool.addDay(orderBeforeTimeToDate, 1);
                }

                timeSpan.setEtime(DateTool.format(isToMorrow, DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }

            long innerTime = getRentalTimeContainRuleTime(timeSpan.getStime(), timeSpan.getEtime(), ruleBefore24Time, ruleAfter24Time, 0);
            timeSpan.setLengthMinute((int) innerTime);
            timeSpans.add(timeSpan);
        }

        Date orderAfterTimeToDate = DateTool.toDate(orderAfterTime, YYYYMMDD_BAR_HHMMSS_COLON);
        String orderAfterTimeToDateHHMM = DateTool.format(orderAfterTimeToDate, DatePattern.HHMM_COLON);
        // 结束时间段
        if (DateTool.checkDateToBetween(orderAfterTimeToDateHHMM, ruleBefore24Time, ruleAfter24Time)) {
            // 开始时间到规则结束时间
            TimeSpan timeSpan = new TimeSpan();
            timeSpan.setSort(99);
            timeSpan.setStime(DateTool.format(DateTool.addDay(orderAfterTimeToDate, 0), DatePattern.YYYYMMDD_BAR).concat(" " + ruleBefore24Time + ZERO2_OCLOCK));
            timeSpan.setEtime(DateTool.format(orderAfterTimeToDate, YYYYMMDD_BAR_HHMMSS_COLON));

            long innerTime = getRentalTimeContainRuleTime(timeSpan.getStime(), timeSpan.getEtime(), ruleBefore24Time, ruleAfter24Time, 0);
            timeSpan.setLengthMinute((int) innerTime);
            timeSpans.add(timeSpan);
        }

        // 中段
        int zongTime = (int) TimeTool.getRentalTimeContainRuleTime(orderBeforeTime, orderAfterTime, ruleBefore24Time, ruleAfter24Time, initTime);
        int ruleTimeCha = DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBefore24Time, ruleAfter24Time);
        for (int i = 0; i < timeSpans.size(); i++) {
            zongTime = zongTime - timeSpans.get(i).getLengthMinute();
        }

        if (zongTime >= ruleTimeCha) {
            TimeSpan timeSpan = new TimeSpan();
            timeSpan.setSort(2);
            if (timeSpans.size() == 0) {
                timeSpan.setStime(DateTool.format(DateTool.addDay(orderBeforeTimeToDate, 0), DatePattern.YYYYMMDD_BAR).concat(" " + ruleBefore24Time + ZERO2_OCLOCK));
                timeSpan.setEtime(DateTool.format(DateTool.addDay(orderAfterTimeToDate, 0), DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }
            if (timeSpans.size() == 1 && timeSpans.get(0).getSort() == 1) {
                timeSpan.setStime(DateTool.format(DateTool.addDay(orderBeforeTimeToDate, 1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleBefore24Time + ZERO2_OCLOCK));
                timeSpan.setEtime(DateTool.format(DateTool.addDay(orderBeforeTimeToDate, 1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }
            if (timeSpans.size() == 1 && timeSpans.get(0).getSort() == 99) {
                timeSpan.setStime(DateTool.format(DateTool.addDay(orderAfterTimeToDate, -1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleBefore24Time + ZERO2_OCLOCK));
                timeSpan.setEtime(DateTool.format(DateTool.addDay(orderAfterTimeToDate, -1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }
            if (timeSpans.size() == 2) {
                timeSpan.setStime(DateTool.format(DateTool.addDay(orderBeforeTimeToDate, 1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleBefore24Time + ZERO2_OCLOCK));
                timeSpan.setEtime(DateTool.format(DateTool.addDay(orderAfterTimeToDate, -1), DatePattern.YYYYMMDD_BAR).concat(" " + ruleAfter24Time + ZERO2_OCLOCK));
            }

            timeSpan.setLengthMinute(zongTime);
            timeSpans.add(timeSpan);
        }

        timeSpans.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));

        return timeSpans;
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
        /**
         * 核算时间租车时长开始于优惠时间时收initTime不需要计算进入优惠时间段里
         */
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
                this.rentalAfterTime = DateTool.format(DateTool.addMinute(rentalAfterTime, 1), YYYYMMDD_BAR_HHMMSS_COLON);
            }

            invoke();

            // 还原
            this.rentalAfterTime = rentalAfterTime;
            if(rentalAfterTimeIs000000(rentalAfterTime)){
                this.rentalAfterTime = DateTool.format(DateTool.addSecond(rentalAfterTime, 1), YYYYMMDD_BAR_HHMMSS_COLON);
            }

            if (isSameMinuteSecondOfTheDay()) {
                // 同一秒钟+1秒 计算
                this.rentalAfterTime = DateTool.format(DateTool.addSecond(rentalAfterTime, 1), YYYYMMDD_BAR_HHMMSS_COLON);
            }


        }

        private boolean rentalAfterTimeIs000000(String rentalAfterTime) {
            return rentalAfterTime.endsWith(ZERO3_OCLOCK);
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
         * @return 布尔值
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
                beforeYh = ruleBeforeTime.substring(0, 5) + ZERO2_OCLOCK;
            }

            // 优惠时间段开始不包含于总时间,优惠结束时间段包含于
            if (yhBefore < zBefore && yhAfter >= zBefore) {
                beforeYh = rentalBeforeTime.substring(11);
            }

            // 优惠时间段结束包含于总时间
            if (yhAfter >= zBefore && yhAfter <= zAfter) {
                // 优惠结束时间
                afterYh = ruleAfterTime.substring(0, 5) + ZERO2_OCLOCK;
            }

            // 优惠时间段结束不包含于总时间,优惠开始时间段包含于
            if (yhAfter > zAfter && yhBefore <= zAfter) {
                // 优惠结束时间
                afterYh = rentalAfterTime.substring(11);
            }

            // initTime 起步时间到优惠时间之间 计算 优惠时间
            if (DateTool.getNumberOfMinutesBetweenTheTwoTime(rentalBeforeTime.substring(11), ruleBeforeTime.concat(ZERO2_OCLOCK)) >= 0) {
                hssj = initTime - DateTool.getNumberOfMinutesBetweenTheTwoTime(rentalBeforeTime.substring(11), ruleBeforeTime.concat(ZERO2_OCLOCK));
                if (hssj < 0) {
                    hssj = 0;
                }
            }

            if (DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime.concat(ZERO2_OCLOCK), rentalBeforeTime.substring(11)) >= 0) {

                long jsYhTime = DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime, ruleAfterTime);
                if (DateTool.getNumberOfMinutesBetweenTheTwoTime(ruleBeforeTime.concat(ZERO2_OCLOCK), rentalBeforeTime.substring(11)) < jsYhTime) {
                    hssj = initTime;
                }
            }

            return this;
        }
    }
}