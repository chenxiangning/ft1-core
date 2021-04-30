package com.rental.hkr.tennis.notscan.date;

import com.rental.hkr.tennis.Slf4jTool;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rental.hkr.tennis.notscan.date.TimeInterval.SECONDS_PER_HOUR;
import static com.rental.hkr.tennis.notscan.date.TimeInterval.SECONDS_PER_MINUTE;
import static java.util.Calendar.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 15:11
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 *
 *
 *
 * <h3>常用方法:</h3>
 *
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4" summary="">
 * <tr style="background-color:#ccccff">
 * <th align="left">字段</th>
 * <th align="left">说明</th>
 * </tr>
 * <tr valign="top">
 * <td>字符串转日期</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#toDate(String, String...)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top" style="background-color:#eeeeff">
 * <td>日期转字符串</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#format(Date, String)} </li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top">
 * <td>日期加减</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#addDay(Date, int)}</li>
 * <li>{@link DateTool#addHour(Date, int)}</li>
 * <li>{@link DateTool#addMinute(Date, int)}</li>
 * <li>{@link DateTool#addMonth(Date, int)}</li>
 * <li>{@link DateTool#addSecond(Date, int)}</li>
 * <li>{@link DateTool#addWeek(Date, int)}</li>
 * <li>{@link DateTool#addYear(Date, int)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top" style="background-color:#eeeeff">
 * <td>获得日期某部值</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#getDayOfMonth(Date)}</li>
 * <li>{@link DateTool#getDayOfWeek(Date)}</li>
 * <li>{@link DateTool#getDayOfYear(Date)}</li>
 * <li>{@link DateTool#getHourOfDay(Date)}</li>
 * <li>{@link DateTool#getHourOfYear(Date)}</li>
 * <li>{@link DateTool#getMinute(Date)}</li>
 * <li>{@link DateTool#getMonth(Date)}</li>
 * <li>{@link DateTool#getSecond(Date)}</li>
 * <li>{@link DateTool#getYear(Date)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top" style="background-color:#eeeeff">
 * <td>判断闰年</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#isLeapYear(int)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top">
 * <td>判断相等</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#isEquals(Date, Date, String)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top" style="background-color:#eeeeff">
 * <td>判断早晚</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#isBefore(Date, Date)}</li>
 * <li>{@link DateTool#isBefore(Date, Date)}</li>
 * </ul>
 * </td>
 * </tr>
 * <tr valign="top">
 * <td>判断日期区间</td>
 * <td>
 * <ul>
 * <li>{@link DateTool#isInTime(Date, Date, Date)}</li>
 * <li>{@link DateTool#checkDateToBetween(java.lang.String data, java.lang.String before, java.lang.String after)}</li>
 * <li>{@link DateTool#getNumberOfMinutesBetweenTheTwoTime(java.lang.String before, java.lang.String after)}</li>
 * </ul>
 * </td>
 * </tr>
 * </table>
 * </blockquote>
 *
 * @author cxn
 */
public final class DateTool {

    private static final String D_012345_D = "[012]\\d:[012345]\\d";

    /**
     * Do not instantiate DateTool.
     */
    private DateTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }


    /**
     * 指定日期 <code>date</code>,加减年份.
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     * <h3>示例:</h3>
     * <blockquote>
     * <pre class="code">
     * DateTool.addYear(2012-06-29 00:33:05,5)   =20<span style="color:red">17</span>-06-29 00:33:05
     * DateTool.addYear(2012-06-29 00:33:05,-5)  =20<span style="color:red">07</span>-06-29 00:33:05
     * </pre>
     * </blockquote>
     *
     * @param date 任意时间
     * @param year 加减年数 ,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>year==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see Calendar#YEAR
     * @see org.apache.commons.lang3.time.DateUtils#addYears(Date, int)
     */
    public static Date addYear(Date date, int year) {
        return DateUtils.addYears(date, year);
    }

    /**
     * 指定日期 <code>date</code>加减月份.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addMonth(2012-10-16 23:12:43,5)  =2013-03-16 23:12:43.932
     * DateTool.addMonth(2012-10-16 23:12:43,-5) =2012-05-16 23:12:43.943
     * </pre>
     *
     * </blockquote>
     *
     * @param date  任意时间
     * @param month 加减月份, <span style="color:red">可以是负数</span>,表示前面多少<br>
     *              比如-3 表示 3个月之前
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>month==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see Calendar#MONTH
     * @see org.apache.commons.lang3.time.DateUtils#addMonths(Date, int)
     */
    public static Date addMonth(Date date, int month) {
        return DateUtils.addMonths(date, month);
    }

    /**
     * 指定日期 <code>date</code>加减天数.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addDay(2012-06-29 00:42:26,5)    =2012-07-04 00:42:26
     * DateTool.addDay(2012-06-29 00:42:26,-5)   =2012-06-24 00:42:26
     * DateTool.addDay(2014-12-31 02:10:05,5)    =2015-01-05 02:10:05.000
     * DateTool.addDay(2014-01-01 02:10:05,-5)   =2013-12-27 02:10:05.000
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @param day  需要加减的天数,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>day==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see Calendar#DAY_OF_MONTH
     * @see org.apache.commons.lang3.time.DateUtils#addDays(Date, int)
     */
    public static Date addDay(Date date, int day) {
        // Calendar.DAY_OF_MONTH 它与 Calendar.DATE 是同义词.一个月中第一天的值为 1.
        return DateUtils.addDays(date, day);
    }

    public static Date addDay(String date, int day) {
        // Calendar.DAY_OF_MONTH 它与 Calendar.DATE 是同义词.一个月中第一天的值为 1.
        return DateUtils.addDays(toDate(date, DatePattern.DATE_PATTERNS), day);
    }

    /**
     * 指定日期 <code>date</code>加减星期 .
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addWeek(2012-06-29 00:45:18,5)   =2012-08-03 00:45:18
     * DateTool.addWeek(2012-06-29 00:45:18,-5)  =2012-05-25 00:45:18
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @param week 需要加减的星期数,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>week==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see org.apache.commons.lang3.time.DateUtils#addWeeks(Date, int)
     */
    public static Date addWeek(Date date, int week) {
        return DateUtils.addWeeks(date, week);
    }

    /**
     * 指定日期 <code>date</code>加减小时.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addHour(2012-06-29 00:46:24,5)   =2012-06-29 05:46:24
     * DateTool.addHour(2012-06-29 00:46:24,-5)  =2012-06-28 19:46:24
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @param hour 加减小时数,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>hour==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see org.apache.commons.lang3.time.DateUtils#addHours(Date, int)
     */
    public static Date addHour(Date date, int hour) {
        return DateUtils.addHours(date, hour);
    }

    /**
     * 指定日期 <code>date</code>加减分钟.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addMinute(2012-10-16 23:20:33,180)   =2012-10-17 02:20:33.669
     * DateTool.addMinute(2012-10-16 23:20:33,-180)  =2012-10-16 20:20:33.669
     * </pre>
     *
     * </blockquote>
     *
     * @param date   任意时间
     * @param minute 加减分钟数,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>minute==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see org.apache.commons.lang3.time.DateUtils#addMinutes(Date, int)
     */
    public static Date addMinute(Date date, int minute) {
        return DateUtils.addMinutes(date, minute);
    }

    public static Date addMinute(String date, int minute) {
        return DateUtils.addMinutes(toDate(date, DatePattern.DATE_PATTERNS), minute);
    }

    /**
     * 指定日期 <code>date</code>加减秒.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addSecond(2012-10-16 23:22:02,180)   = 2012-10-16 23:25:02.206
     * DateTool.addSecond(2012-10-16 23:22:02,-180)  = 2012-10-16 23:19:02.206
     * </pre>
     *
     * </blockquote>
     *
     * @param date   任意时间
     * @param second 加减秒,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>second==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see org.apache.commons.lang3.time.DateUtils#addSeconds(Date, int)
     */
    public static Date addSecond(Date date, int second) {
        return DateUtils.addSeconds(date, second);
    }

    public static Date addSecond(String date, int second) {
        return DateUtils.addSeconds(toDate(date, DatePattern.DATE_PATTERNS), second);
    }


    /**
     * 指定日期 <code>date</code>加减毫秒.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>结果会自动跨月,跨年计算.</li>
     * <li>传入的参数 <code>date</code> 不会改变</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.addMillisecond(2015-09-07 13:35:02.769,5000)     =2015-09-07 13:35:07.769
     * DateTool.addMillisecond(2015-09-07 13:35:02.769,-5000)    =2015-09-07 13:34:57.769
     * </pre>
     *
     * </blockquote>
     *
     * @param date        任意时间
     * @param millisecond 加减毫秒,<span style="color:red">可以是负数</span>,表示前面多少<br>
     * @return 如果 <code>date</code>是null,抛出 {@link java.lang.IllegalArgumentException}<br>
     * 如果 <code>millisecond==0</code>,那么什么都不做,返回 <code>date</code>,参见 {@link GregorianCalendar#add(int, int)}
     * @throws IllegalArgumentException 如果 <code>date</code> 是<code>null</code>
     * @see org.apache.commons.lang3.time.DateUtils#addMilliseconds(Date, int)
     * @since 1.4.1
     */
    public static Date addMillisecond(Date date, int millisecond) {
        return DateUtils.addMilliseconds(date, millisecond);
    }


    /**
     * 获得指定日期 <code>date</code>中的<b>年份</b>.
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getYear(toDate("2012-06-29 00:26:53", COMMON_DATE_AND_TIME))    = 2012
     * DateTool.getYear(toDate("2016-07-16", COMMON_DATE))                      = 2016
     * DateTool.getYear(toDate("2016-13-16", COMMON_DATE))                      = 2017
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#YEAR
     */
    public static int getYear(Date date) {
        return CalendarTool.getFieldValue(date, YEAR);
    }

    /**
     * 获得指定日期 <code>date</code>中的<b>月份</b> <span style="color:red">(已经+1处理)</span>.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getMonth(<code>2012-06-29</code>)    =6
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#MONTH
     */
    public static int getMonth(Date date) {
        return 1 + CalendarTool.getFieldValue(date, MONTH);
    }

    /**
     * 指定日期 <code>date</code>年中的<b>星期数</b>.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getWeekOfYear(2014-06-03)    =23
     * DateTool.getWeekOfYear(2014-01-01)    =1
     * DateTool.getWeekOfYear(2014-12-29)    =1
     * DateTool.getWeekOfYear(2014-12-20)    =51
     * DateTool.getWeekOfYear(2014-12-26)    =52
     * </pre>
     *
     * </blockquote>
     *
     * <h3>注意:</h3>
     *
     * <blockquote>
     * <ol>
     * <li>一年中第一个星期的值为 1,一年52(365/7=52.14)个星期</li>
     * <li>2014年的1-1 1-2 1-3 1-4 得出的{@link Calendar#WEEK_OF_YEAR} 是1; <br>
     * 2014年的12-28 12-29 12-30 12-31 得出的{@link Calendar#WEEK_OF_YEAR} 也是1</li>
     * <li>{@link Calendar#setMinimalDaysInFirstWeek(int)} 可以来修改第一周最小天数,但是如果设置为7的话
     *
     * <pre class="code">
     * DateTool.getWeekOfYear(2014-01-01)    =52
     * DateTool.getWeekOfYear(2014-12-31)    =52
     * </pre>
     *
     * 可以看出,如果从1月1号算开始第一周的话,这年第一周时间不够我们设置的7天,那么1月1号算上一年的星期</li>
     * </ol>
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#WEEK_OF_YEAR
     * @see Calendar#getFirstDayOfWeek()
     * @see Calendar#getMinimalDaysInFirstWeek()
     * @see Calendar#setMinimalDaysInFirstWeek(int)
     * @since 1.0.7
     */
    public static int getWeekOfYear(Date date) {
        return CalendarTool.getFieldValue(date, WEEK_OF_YEAR);
    }

    /**
     * 获得指定日期 <code>date</code>是当年中的第几天.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getDayOfYear(<code>2013-01-01</code>)    =1
     * DateTool.getDayOfYear(<code>2013-01-05</code>)    =5
     * DateTool.getDayOfYear(<code>2016-12-31</code>)    =366
     * DateTool.getDayOfYear(<code>2016-02-01</code>)    =32
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see Calendar#DAY_OF_YEAR
     * @since 1.0.2
     */
    public static int getDayOfYear(Date date) {
        return CalendarTool.getFieldValue(date, DAY_OF_YEAR);
    }

    /**
     * 获得指定日期 <code>date</code>是当前月的几号.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getDayOfMonth(<code>2012-06-29</code>)    =29
     * DateTool.getDayOfMonth(<code>2013-01-05</code>)    =5
     * DateTool.getDayOfMonth(<code>2016-12-31</code>)    =31
     * DateTool.getDayOfMonth(<code>2016-02-01</code>)    =1
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#DAY_OF_MONTH
     */
    public static int getDayOfMonth(Date date) {
        return CalendarTool.getFieldValue(date, DAY_OF_MONTH);
    }

    /**
     * 获得指定日期<code>date</code><b>在当前星期是星期几</b>.
     *
     * <h3>注意:</h3>
     * <blockquote>
     * <ol>
     * <li>从星期天开始,并且星期天是1</li>
     * <li>{@link Calendar#SUNDAY SUNDAY}、{@link Calendar#MONDAY MONDAY}、{@link Calendar#TUESDAY TUESDAY}、{@link Calendar#WEDNESDAY
     * WEDNESDAY}、 {@link Calendar#THURSDAY THURSDAY}、{@link Calendar#FRIDAY FRIDAY} 和 {@link Calendar#SATURDAY SATURDAY} ,分别对应1-7</li>
     * <li>强烈建议拿上述常量来比较判断,而不是拿数字来比较</li>
     * </ol>
     * </blockquote>
     *
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getDayOfWeek(2012-6-29)  =6  是 {@link Calendar#FRIDAY FRIDAY} 星期5
     * DateTool.getDayOfWeek(2016-08-16)  ={@link Calendar#TUESDAY}
     * DateTool.getDayOfWeek(2016-12-31)  ={@link Calendar#SATURDAY}
     * DateTool.getDayOfWeek(2016-02-01)  ={@link Calendar#MONDAY}
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#DAY_OF_WEEK
     */
    public static int getDayOfWeek(Date date) {
        return CalendarTool.getFieldValue(date, DAY_OF_WEEK);
    }

    /**
     * 获得指定日期<code>date</code>在它<b>一年中的小时数</b>.
     *
     *
     * max value: 8784.
     *
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getHourOfYear(2013-01-01 00:00:05)   =0
     * DateTool.getHourOfYear(2013-01-01 01:00:05)   =1
     * DateTool.getHourOfYear(2013-01-05 12:00:05)   =108
     * DateTool.getHourOfYear(2013-09-09 17:28)      =6041
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @since 1.0.2
     */
    public static int getHourOfYear(Date date) {
        return (getDayOfYear(date) - 1) * 24 + CalendarTool.getFieldValue(date, HOUR_OF_DAY);
    }

    /**
     * 获得指定日期<code>date</code>中的<b>小时</b>(24小时制).
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getHourOfDay(toDate("2012-06-29 00:26:53", COMMON_DATE_AND_TIME)) =0
     * DateTool.getHourOfDay(toDate("2016-07-16 22:34:00", COMMON_DATE_AND_TIME)) =22
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#HOUR_OF_DAY
     */
    public static int getHourOfDay(Date date) {
        return CalendarTool.getFieldValue(date, HOUR_OF_DAY);
    }

    /**
     * 获得指定日期<code>date</code>中的<b>分钟</b>.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getMinute(2012-6-29 00:26:53)    =26
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#MINUTE
     */
    public static int getMinute(Date date) {
        return CalendarTool.getFieldValue(date, MINUTE);
    }

    /**
     * 获得指定日期<code>date</code>中的<b>秒</b>.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getSecond(2012-6-29 00:26:53)    =53
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see CalendarTool#getFieldValue(Date, int)
     * @see Calendar#SECOND
     */
    public static int getSecond(Date date) {
        return CalendarTool.getFieldValue(date, SECOND);
    }


    /**
     * 获得指定日期<code>date</code>在<b>当天中的秒数</b>,最大值86400.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getSecondOfDay(2013-09-09 16:42:41)= 60161
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see TimeInterval#SECONDS_PER_DAY
     * @see TimeInterval#SECONDS_PER_HOUR
     * @see #getSecondOfHour(Date)
     * @since 1.0.2
     */
    public static int getSecondOfDay(Date date) {
        int hour = getHourOfDay(date);
        return hour * SECONDS_PER_HOUR + getSecondOfHour(date);
    }

    /**
     * 获得指定日期<code>date</code>在<b>当前小时中的秒数</b>,最大值3600 {@link TimeInterval#SECONDS_PER_HOUR}.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.getSecondOfHour(2013-09-15 01:15:23)= 923
     * </pre>
     *
     * </blockquote>
     *
     * @param date 任意时间
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * @see TimeInterval#SECONDS_PER_MINUTE
     * @see TimeInterval#SECONDS_PER_HOUR
     * @since 1.0.2
     */
    public static int getSecondOfHour(Date date) {
        int minute = getMinute(date);
        int second = getSecond(date);
        return second + minute * SECONDS_PER_MINUTE;
    }

    /**
     * 判断指定日期 <code>date</code>是否 在 <code>whenDate</code>时间之前.
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * isBefore(null, toDate("2011-03-10", COMMON_DATE))                                =   false
     * isBefore(toDate("2011-05-01", COMMON_DATE), toDate("2011-04-01", COMMON_DATE))   =   false
     * isBefore(toDate("2011-03-05", COMMON_DATE), toDate("2011-03-10", COMMON_DATE))   =   true
     * </pre>
     *
     * </blockquote>
     *
     * @param date     指定日期
     * @param whenDate 比照日期
     * @return 如果 <code>whenDate</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>date</code> 是null,返回false<br>
     * 否则返回 <code>date.before(whenDate)</code>
     * @see java.util.Date#before(Date)
     * @since 1.2.2
     */
    public static boolean isBefore(Date date, Date whenDate) {
        Validate.notNull(whenDate, "whenDate can't be null!");
        return null == date ? false : date.before(whenDate);
    }

    /**
     * 判断指定日期 <code>date</code>是否在 <code>whenDate</code>时间之后.
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * isAfter(null,toDate("2011-05-01", COMMON_DATE))                                 =   false
     * isAfter(toDate("2011-04-01", COMMON_DATE),toDate("2011-05-01", COMMON_DATE))    =   false
     * isAfter(toDate("2011-03-10", COMMON_DATE),toDate("2011-03-05", COMMON_DATE))    =   true
     * </pre>
     *
     * </blockquote>
     *
     * @param date     指定的日期
     * @param whenDate 比照日期
     * @return 如果 <code>whenDate</code> 是null,抛出 {@link NullPointerException} <br>
     * 如果 <code>date</code> 是null,返回false<br>
     * 否则返回 <code>date.after(whenDate)</code>
     * @see java.util.Date#after(Date)
     * @since 1.2.2
     */
    public static boolean isAfter(Date date, Date whenDate) {
        Validate.notNull(whenDate, "whenDate can't be null!");
        return null == date ? false : date.after(whenDate);
    }


    /**
     * 判断指定日期 <code>date</code> 是否在 <code>beginDate</code> 和 <code>endDate</code>两个时间之间.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.isInTime("2012-10-16 23:00:02", "2012-10-10 22:59:00", "2012-10-18 22:59:00") = true
     * </pre>
     *
     * </blockquote>
     *
     * @param date      指定日期
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 如果 <code>date</code> 在 <code>beginDate</code>之后, 并且指定日期 <code>date</code> 在 <code>endDate</code>之前,返回true<br>
     * @throws NullPointerException 如果 <code>date</code> 是null,或者 <code>beginDate</code> 是null 或者 <code>endDate</code> 是null
     * @see Date#after(Date)
     * @see Date#before(Date)
     */
    public static boolean isInTime(Date date, Date beginDate, Date endDate) {
        Validate.notNull(date, "date can't be null!");
        Validate.notNull(beginDate, "beginDate can't be null!");
        Validate.notNull(endDate, "endDate can't be null!");
        return date.after(beginDate) && date.before(endDate);
    }


    /**
     * 验证当前日期是否包含于给定日期之间
     *
     * @param data   字符串中包含 xx:xx 即可 也可以是 yyyy-MM-dd HH:mm:ss
     * @param before xx:xx 只能是这种 HH:mm
     * @param after  xx:xx 只能是这种 HH:mm
     * @return 布尔
     */
    public static boolean checkDateToBetween(String data, String before, String after) {
        Matcher m = Pattern.compile(D_012345_D).matcher(data);
        m.find();
        data = m.group();

        long currentTime = toDate(data, DatePattern.DATE_PATTERNS).getTime();
        long beforeTime = toDate(before, DatePattern.DATE_PATTERNS).getTime();
        long afterTime = toDate(after, DatePattern.DATE_PATTERNS).getTime();

        if (beforeTime < afterTime) {
            return beforeTime <= currentTime && currentTime < afterTime;
        }

        if (beforeTime > afterTime) {
            return (beforeTime <= currentTime && currentTime > afterTime)
                    || currentTime < afterTime;
        }
        return false;
    }


    /**
     * 在相同格式下 <code>datePattern</code>,将两个日期转成字符串判断是否相等.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.isEquals(toDate("2016-06-16 22:59:00", COMMON_DATE_AND_TIME), toDate("2016-06-16", COMMON_DATE), COMMON_DATE) = true
     * </pre>
     *
     * </blockquote>
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>常用于判断两个时间是否是同一个时间段,比如相同day,相同小时,相同年等等</li>
     * </ol>
     * </blockquote>
     *
     * @param date1       日期1
     * @param date2       日期2
     * @param datePattern 格式 {@link DatePattern}
     * @return 相等返回true, 不相等则为false<br>
     * 如果 <code>date1</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>date2</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>pattern</code> 是 null,抛出 {@link NullPointerException}<br>
     * 如果 <code>pattern</code> 是 blank,抛出 {@link IllegalArgumentException}<br>
     * @see #format(Date, String)
     * @see org.apache.commons.lang3.time.DateUtils#isSameDay(Date, Date)
     * @since 1.0.5 change name from isEqual to isEquals
     */
    public static boolean isEquals(Date date1, Date date2, String datePattern) {
        Validate.notNull(date1, "date1 can't be null!");
        Validate.notNull(date2, "date2 can't be null!");

        Validate.notBlank(datePattern, "datePattern can't be blank!");
        return format(date1, datePattern).equals(format(date2, datePattern));
    }

    /**
     * 将指定日期 <code>date</code>转换成特殊格式 <code>datePattern</code> 的字符串.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.format(Tue Oct 16 23:49:21 CST 2012,DatePattern.YYYYMMDD_BAR_HHMMSS_COLON) =2012-10-16 23:49:21.525
     * </pre>
     *
     * </blockquote>
     *
     * @param date        任意时间
     * @param datePattern 模式 {@link DatePattern}
     * @return 如果 <code>date</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>pattern</code> 是 null,抛出 {@link NullPointerException}<br>
     * 如果 <code>pattern</code> 是 blank,抛出 {@link IllegalArgumentException}<br>
     * @see org.apache.commons.lang3.time.DateFormatUtils#format(Date, String)
     * @see "org.joda.time.basics.AbstractDateTime#format(String)"
     * @see <a href="http://stackoverflow.com/questions/5683728/convert-java-util-date-to-string">convert-java-util-date-to-string</a>
     * @see <a href="http://stackoverflow.com/questions/4772425/change-date-format-in-a-java-string">change-date-format-in-a-java-string</a>
     * @since 1.6.0
     */
    public static String format(Date date, String datePattern) {
        Validate.notNull(date, "date can't be null!");
        Validate.notBlank(datePattern, "datePattern can't be blank!");

        return DateFormatUtils.format(date, datePattern);
    }

    /**
     * 将时间字符串 <code>dateString</code> 使用<b>一个或者多个</b>不同的 <code>datePattern</code> 模式按照顺序转换成date类型.
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * DateTool.toDate("2016-02-33", DatePattern.COMMON_DATE)                   = 2016-03-04
     * DateTool.toDate("2016-06-28T01:21:12-0800", "yyyy-MM-dd'T'HH:mm:ssZ")    = 2016-06-28 17:21:12
     * DateTool.toDate("2016-06-28T01:21:12+0800", "yyyy-MM-dd'T'HH:mm:ssZ")    = 2016-06-28 01:21:12
     * </pre>
     *
     * </blockquote>
     *
     * <h3>注意:</h3>
     * <blockquote>
     * <ol>
     * <li>转换的时候,使用日历的<b>宽松模式</b>,参见 {@link java.text.DateFormat#setLenient(boolean)},即支持传入"2016-02-33",会转换成 2016-03-04</li>
     * <li>如果能解析所有的字符串,那么视为成功</li>
     * <li>如果没有任何的模式匹配,将会抛出异常</li>
     * <li>如果转换有异常,会将 {@link ParseException} 转成 {@link IllegalArgumentException} 返回,是 UnCheckedException异常 ,不需要强制catch处理</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * 经常我们会看到小伙伴写出下面的代码:
     *
     * <pre class="code">
     * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
     * Date publishTimeDate = null;
     * try{
     * publishTimeDate = format.parse(publishTime);
     * }catch (ParseException e1){
     * e1.printStackTrace();
     * }
     * </pre>
     *
     *
     * 可以看到直接使用 {@code SimpleDateFormat} 来写代码的话,代码行数较多,并且还需要自行处理 ParseException checkedException异常, 而且catch里面一般都是写的废话
     *
     *
     *
     * 此时你可以一行代码搞定:
     *
     *
     * <pre class="code">
     * Date publishTimeDate = DateTool.toDate(publishTime, DatePattern.YYYYMMDD_BAR_HHMMSSSSS_COLON);
     * </pre>
     *
     * </blockquote>
     *
     * @param dateString   时间字符串
     * @param datePatterns 模式,时间字符串的模式{@link DatePattern}
     * @return 如果 <code>dateString</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>dateString</code> 是blank,抛出 {@link IllegalArgumentException}<br>
     * 如果 <code>datePatterns</code> 是 null,抛出 {@link NullPointerException}<br>
     * 如果 <code>datePatterns</code> 是 empty,抛出 {@link IllegalArgumentException}<br>
     * 如果 <code>datePatterns</code> 有元素是 null,抛出 {@link IllegalArgumentException}<br>
     * @see org.apache.commons.lang3.time.DateUtils#parseDate(String, String...)
     * @see <a href="http://stackoverflow.com/questions/4216745/java-string-to-date-conversion/">java-string-to-date-conversion</a>
     * @see <a href="http://stackoverflow.com/questions/4216745/java-string-to-date-conversion/22180505#22180505">java-string-to-date-
     * conversion/22180505#22180505</a>
     * @see <a href="http://stackoverflow.com/questions/2735023/convert-string-to-java-util-date">convert-string-to-java-util-date</a>
     * @since 1.7.3 change param to datePatterns array
     */
    public static Date toDate(String dateString, String... datePatterns) {
        Validate.notBlank(dateString, "dateString can't be blank!");

        Validate.notEmpty(datePatterns, "datePatterns can't be null!");
        Validate.noNullElements(datePatterns, "datePatterns can't has null datePattern");

        try {
            return DateUtils.parseDate(dateString, datePatterns);
        } catch (ParseException e) {
            String pattern = "parse dateString [{}] use patterns:[{}] to date exception,message:[{}]";
            throw new IllegalArgumentException(Slf4jTool.format(pattern, dateString, datePatterns, e.getMessage()), e);
        }
    }

    /**
     * 判断指定年 <code>year</code> 是否为闰年 .
     *
     *
     * 规则: {@code (year % 4 == 0 && year % 100 != 0) || year % 400 == 0}
     *
     *
     * <h3>闰年原因:</h3>
     *
     * <blockquote>
     *
     *
     * 地球绕太阳运行周期为<b>365天5小时48分46秒</b>(合365.24219天)即一回归年(tropical year).<br>
     * 公历的平年只有365日,比回归年短约0.2422日,所余下的时间约为四年累计一天,故四年于2月加1天,使当年的历年长度为366日,这一年就为闰年.<br>
     *
     *
     *
     * 现行公历中每400年有97个闰年.按照每四年一个闰年计算,平均每年就要多算出0.0078天,这样经过四百年就会多算出大约3天来,<br>
     * 因此,每四百年中要减少三个闰年.
     *
     *
     *
     * 所以规定,公历年份是整百数的,必须是400的倍数的才是闰年,不是400的倍数的,虽然是100的倍数,也是平年,<br>
     * 这就是通常所说的:<b>四年一闰,百年不闰,四百年再闰</b>.<br>
     *
     * 例如,2000年是闰年,1900年则是平年.<br>
     *
     * </blockquote>
     *
     * <h3>说明:</h3>
     * <blockquote>
     * 需要注意的是,有些文章说3200 是平年, 参见http://baike.baidu.com/item/闰年/27098#6 精确计算方法,但是 jdk中 判定3200 依然是闰年,并且
     * https://en.wikipedia.org/wiki/Leap_year 也没有提及3200是平年的故事
     * {@link java.util.GregorianCalendar#gregorianCutoverYearJulian 1582}年之前是四年一闰,
     * {@link java.util.GregorianCalendar#gregorianCutoverYearJulian 1582}年之后 四年一闰,百年不闰,四百年再闰
     * </blockquote>
     *
     * @param year 年份
     * @return 四年一闰, 百年不闰, 四百年再闰
     * @see GregorianCalendar#isLeapYear(int)
     * @see <a href="https://en.wikipedia.org/wiki/Leap_year">Leap_year</a>
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }


    /**
     * 获取两个时间之间的分钟数 <code>before</code> 使用<b>一个或者多个</b>不同的 <code>after</code>
     *
     * <h3>示例:</h3>
     *
     * <blockquote>
     *
     * <pre class="code">
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("23:00", "00:01"),61);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("20:00", "20:30"),30);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("01:00", "10:11"),551);
     * Assert.assertEquals(DateTool.getNumberOfMinutesBetweenTheTwoTime("2017-09-25 14:15:00", "2017-09-26 14:30:00"),1455);
     * </pre>
     *
     * </blockquote>
     *
     * @param before 开始时间
     * @param after  结束时间
     * @return 获取两个时间之间的分钟数
     */

    public static int getNumberOfMinutesBetweenTheTwoTime(String before, String after) {
        long beforeTime = toDate(before, DatePattern.DATE_PATTERNS).getTime();
        long afterTime = toDate(after, DatePattern.DATE_PATTERNS).getTime();

        long s = afterTime - beforeTime;
        if (s > 0 && s < TimeInterval.MILLISECOND_PER_MINUTE) {
            return 1;
        }

        int result = new BigDecimal(afterTime - beforeTime).divide(new BigDecimal(TimeInterval.MILLISECOND_PER_MINUTE), 0, BigDecimal.ROUND_CEILING).intValue();
        if (result < 0) {
            return result + TimeInterval.MINUTE_PER_DAY;
        }

        return result;
    }

}
