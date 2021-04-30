package com.rental.hkr.tennis.springscan.cache;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/27 0:16
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
public class DemoBean implements Serializable {
    public String name;

    public DemoBean(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
