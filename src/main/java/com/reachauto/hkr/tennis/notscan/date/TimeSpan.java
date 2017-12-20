package com.reachauto.hkr.tennis.notscan.date;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-12-04 13:22
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
public class TimeSpan {
    //  排序
    private Integer sort;
    private String stime;
    private String etime;
    private int lengthMinute;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public int getLengthMinute() {
        return lengthMinute;
    }

    public void setLengthMinute(int lengthMinute) {
        this.lengthMinute = lengthMinute;
    }

}