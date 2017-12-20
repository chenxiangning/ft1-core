package com.reachauto.hkr.tennis.notscan.date;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 15:34
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public final class CalendarTool {
    /**
     * Do not instantiate CalendarTool.
     */
    private CalendarTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 一天开始,<code>00:00:00.000</code>
     *
     * @param calendar the calendar
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}
     * @see Calendar#set(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @see Calendar#SECOND
     * @see Calendar#MILLISECOND
     * @see org.apache.commons.lang3.time.DateUtils#truncate(Calendar, int)
     * @since 1.3.0
     */
    static Calendar resetDayBegin(Calendar calendar) {
        Validate.notNull(calendar, "calendar can't be null!");
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一天结束,最后的时间 <code>23:59:59.999</code>
     *
     * @param calendar the calendar
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}
     * @see Calendar#set(int, int)
     * @see Calendar#HOUR_OF_DAY
     * @see Calendar#MINUTE
     * @see Calendar#SECOND
     * @see Calendar#MILLISECOND
     * @since 1.3.0
     */
    static Calendar resetDayEnd(Calendar calendar) {
        Validate.notNull(calendar, "calendar can't be null!");
        calendar.set(HOUR_OF_DAY, 23);
        calendar.set(MINUTE, 59);
        calendar.set(SECOND, 59);
        calendar.set(MILLISECOND, 999);
        return calendar;
    }

    /**
     * 一年结束,最后的时间 <code>12月31号 23:59:59.999</code>
     *
     * @param calendar the calendar
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}
     * @see #resetDayEnd(Calendar)
     * @since 1.3.0
     */
    static Calendar resetYearEnd(Calendar calendar) {
        Validate.notNull(calendar, "calendar can't be null!");
        calendar.set(MONTH, DECEMBER);
        calendar.set(DAY_OF_MONTH, 31);
        return resetDayEnd(calendar);
    }

    // [end]

    //**************************************************************************************

    /**
     * 将 {@link Calendar} 转成 {@link Date}.
     *
     * @param calendar calendar
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}
     * @see java.util.Calendar#getTime()
     */
    static Date toDate(Calendar calendar) {
        Validate.notNull(calendar, "calendar can't be null!");
        return calendar.getTime();
    }

    /**
     * 将{@link Calendar}转成{@link String}.
     *
     * @param calendar    calendar
     * @param datePattern 日期pattern {@link DatePattern}
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}
     */
    static String toString(Calendar calendar, String datePattern) {
        Date date = toDate(calendar);
        return DateTool.format(date, datePattern);
    }

    //*********************************************************************************************

    /**
     * 获得日历字段值.
     *
     * @param date  date
     * @param field Calendar字段:<br>
     *              月份:{@link Calendar#MONTH}(真实值需要加1处理),<br>
     *              日:{@link Calendar#DAY_OF_MONTH},<br>
     *              年份:{@link Calendar#YEAR}<br>
     *              ...
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}<br>
     * @see #getFieldValue(Calendar, int)
     * @since 1.3.0
     */
    static int getFieldValue(Date date, int field) {
        Calendar calendar = toCalendar(date);
        return getFieldValue(calendar, field);
    }

    static Calendar toCalendar(Date date) {
        Validate.notNull(date, "date can't be null!");
        return DateUtils.toCalendar(date);
    }

    /**
     * 获得日历字段值.
     *
     * @param calendar the calendar
     * @param field    Calendar字段:<br>
     *                 月份:{@link Calendar#MONTH}(真实值需要加1处理),<br>
     *                 日:{@link Calendar#DAY_OF_MONTH},<br>
     *                 年份:{@link Calendar#YEAR}<br>
     *                 ...
     * @return 如果 <code>calendar</code> 是null,抛出 {@link NullPointerException}<br>
     * @see java.util.Calendar#get(int)
     * @since 1.3.0
     */
    static int getFieldValue(Calendar calendar, int field) {
        Validate.notNull(calendar, "calendar can't be null!");
        return calendar.get(field);
    }

}
