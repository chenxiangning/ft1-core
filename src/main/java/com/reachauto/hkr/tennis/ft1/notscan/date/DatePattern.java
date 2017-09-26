package com.reachauto.hkr.tennis.ft1.notscan.date;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/23 22:12
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 * 常用的日期格式化样式
 */
public class DatePattern {


    public static final String YYYYMMDD_BAR_HHMMSSSSS_COLON = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYYMMDD_BAR_HHMMSS_COLON = "yyyy-MM-dd HH:mm:ss";
    public static final String YYMMDD_BAR_HHMMSS_COLON = "yy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD_BAR_HHMM_COLON = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD_BAR = "yyyy-MM-dd";
    public static final String YYMMDD_BAR = "yy-MM-dd";
    public static final String YYYYMM_BAR = "yyyy-MM";
    public static final String MMDD_BAR_HHMM_COLON = "MM-dd HH:mm";
    public static final String MMDD_BAR = "MM-dd";

    public static final String YYYYMMDD_CHINESE_HHMMSS_COLON = "yyyy年MM月dd日 HH:mm:ss";
    public static final String HHMM_COLON = "HH:mm";
    public static final String HHMMSS_COLON = "HH:mm:ss";


    public static final String YYYYMMDD_SLASH_HHMMSS_COLON = "yyyy/MM/dd HH:mm:ss";
    public static final String YYMMDD_SLASH_HHMMSS_COLON = "yy/MM/dd HH:mm:ss";
    public static final String YYMMDD_SLASH_HHMM_COLON = "yy/MM/dd HH:mm";
    public static final String YYYYMMDD_SLASH_HHMM_COLON = "yyyy/MM/dd HH:mm";
    public static final String YYYYMMDD_SLASH = "yyyy/MM/dd";
    public static final String YYYYMM_SLASH = "yyyy/MM";


    public static final String YYYYMMDD_POINT_HHMMSS_COLON = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYYMMDD_POINT_HHMM_COLON = "yyyy/MM/dd HH:mm";
    public static final String YYMMDD_POINT_HHMM_COLON = "yy/MM/dd HH:mm";
    public static final String YYYYMMDD_POINT = "yyyy.MM.dd";
    public static final String YYYYMM_POINT = "yyyy.MM";


    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HHMMSS = "HHmmss";
    public static final String HHMM = "HHmm";
    public static final String MMSS = "mmss";

    public static final String HH_UPPER_CASE = "HH";
    public static final String MM_UPPER_CASE = "MM";
    public static final String YY_LOWERCASE = "yy";
    public static final String YYYY_LOWERCASE = "yyyy";
    public static final String MM_LOWERCASE = "mm";

    public static final String[] DATE_PATTERNS = {
            YYYYMMDD_BAR_HHMMSSSSS_COLON, YYYYMMDD_BAR_HHMMSS_COLON, YYMMDD_BAR_HHMMSS_COLON, YYYYMMDD_SLASH_HHMMSS_COLON,
            YYYYMMDD_POINT_HHMMSS_COLON, YYMMDDHHMMSS, HHMM_COLON, YYYYMMDDHHMMSS, HHMMSS_COLON, YYYYMMDD_BAR_HHMM_COLON, YYYYMMDD_BAR,
            YYYYMMDD_CHINESE_HHMMSS_COLON, YYYYMM_BAR
    };

    private DatePattern() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }
}
