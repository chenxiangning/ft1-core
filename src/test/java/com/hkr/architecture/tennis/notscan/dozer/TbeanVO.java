package com.hkr.architecture.tennis.notscan.dozer;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 23:58
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
public class TbeanVO {

    private String ooooxxx;
    private Integer kkkkk;
    private String name;
    private Integer age;
    @Expose
    private int code;
    @Expose
    private Double fen;
    @Expose
    private String xxxxx;
    @Expose
    private Date time;

    private Date createAt;
    @Expose
    private Date curtime;

    public Integer getKkkkk() {
        return kkkkk;
    }

    public void setKkkkk(Integer kkkkk) {
        this.kkkkk = kkkkk;
    }

    public String getOoooxxx() {
        return ooooxxx;
    }

    public void setOoooxxx(String ooooxxx) {
        this.ooooxxx = ooooxxx;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getCurtime() {
        return curtime;
    }

    public void setCurtime(Date curtime) {
        this.curtime = curtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Double getFen() {
        return fen;
    }

    public void setFen(Double fen) {
        this.fen = fen;
    }

    public String getXxxxx() {
        return xxxxx;
    }

    public void setXxxxx(String xxxxx) {
        this.xxxxx = xxxxx;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "ooooxxx='" + ooooxxx + '\'' +
                "kkkkk='" + kkkkk + '\'' +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
                ", fen=" + fen +
                ", xxxxx='" + xxxxx + '\'' +
                ", time=" + time +
                ", createAt=" + createAt +
                ", curtime=" + curtime +
                '}';
    }
}
