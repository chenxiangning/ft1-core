package com.reachauto.hkr.tennis.notscan.gson;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/21 22:46
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public class Dage {
    private String mz;
    private List<Gbean> gbeans;

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public List<Gbean> getGbeans() {
        return gbeans;
    }

    public void setGbeans(List<Gbean> gbeans) {
        this.gbeans = gbeans;
    }

    @Override
    public String toString() {
        return "Dage{" +
                "mz='" + mz + '\'' +
                ", gbeans=" + gbeans +
                '}';
    }
}
