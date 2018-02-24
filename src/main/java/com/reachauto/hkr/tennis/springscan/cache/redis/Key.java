package com.reachauto.hkr.tennis.springscan.cache.redis;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:24
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public class Key {

    private StringBuilder trueKey;

    public Key() {
        trueKey = new StringBuilder("hkr:cache:");
    }

    public Key(String trueKey) {
        this.trueKey = new StringBuilder(trueKey);
    }

    public String getTrueKey() {
        return this.trueKey.toString();
    }

    public Key append(Object o) {
        this.trueKey.append(o);
        return this;
    }

    /**
     * 冒号添加
     *
     * @return 返回this
     */
    public Key colon() {
        this.append(":");
        return this;
    }

    public static Key build(String keyVal){
        return new Key(keyVal);
    }
}
