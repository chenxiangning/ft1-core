package com.rental.hkr.tennis.guavac;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 14:30
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description: 逆向视图,key and val 都不可重复
 * 重复的key最后位覆盖
 */
public class BiMapTest {

    @Test
    public void democ() {
        BiMap biMap = HashBiMap.create();

        biMap.put(1,"陈湘宁");
        biMap.put(2,"线程");
        biMap.put(3,"为");

        System.out.println(biMap);

        System.out.println(biMap.get(1));
        System.out.println(biMap.inverse().get("线程"));
        System.out.println(biMap.inverse().getOrDefault("陈湘宁", "22"));
        System.out.println(biMap.inverse().getOrDefault("2陈湘宁", "22"));

        System.out.println(biMap.getOrDefault(3, "大大"));

        System.out.println(biMap.forcePut(3, "是是是"));
        System.out.println(biMap);

    }
}
