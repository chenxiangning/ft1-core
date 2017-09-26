package com.reachauto.hkr.tennis.ft1.springscan.cache;

import com.reachauto.hkr.tennis.ft1.AbstractJUnit;
import com.reachauto.hkr.tennis.ft1.springscan.cache.redis.Key;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class HkrKeyValCacheTest extends AbstractJUnit {
    @Autowired
    @Qualifier(value = "hkrKeyValStrSerializeSpringRedisCache")
    private HkrKeyValCache hkrKeyValCache;

    @Autowired
    @Qualifier(value = "hkrKeyHashStrSerializeSpringRedisCache")
    private HkrKeyHashCache hkrKeyHashCache;

    @Test
    public void demo() {
        System.out.println(hkrKeyValCache);
        System.out.println(hkrKeyHashCache);
        hkrKeyValCache.set(new Key().append("demo.key").colon().append("demo"), "demo");
        System.out.println(hkrKeyValCache.get(new Key().append("demo.key").colon().append("demo")));
    }

    @Test
    public void hashMapSetTest() {

        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("111111", "{1:1,2:2}");
        stringStringMap.put("222222", "{2:1,2:2}");
        stringStringMap.put("333333", "{3:1,2:2}");
        stringStringMap.put("444444", "{4:1,2:2}");

        Key key = new Key().append("demoz.key").colon().append("hash.key.1");

        hkrKeyHashCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrKeyHashCache.getStrValMap(key));


    }

    @Test
    public void putGetDeleteTest() {

        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("111111", "{1:1,2:2}");
        stringStringMap.put("222222", "{2:1,2:2}");
        stringStringMap.put("333333", "{3:1,2:2}");
        stringStringMap.put("444444", "{4:1,2:2}");

        Key key = new Key().append("demoz.key").colon().append("hash.key.2");

        hkrKeyHashCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrKeyHashCache.getStrValMap(key));


        hkrKeyHashCache.put(key, "333333", "bian");

        Assert.assertEquals(hkrKeyHashCache.get(key, "111111"), "{1:1,2:2}");
        Assert.assertEquals(hkrKeyHashCache.get(key, "222222"), "{2:1,2:2}");
        Assert.assertEquals(hkrKeyHashCache.get(key, "333333"), "bian");
        Assert.assertEquals(hkrKeyHashCache.get(key, "444444"), "{4:1,2:2}");


        Assert.assertEquals(hkrKeyHashCache.deleteHashKey(key, "222222", "444444"), 2);

        System.out.println(hkrKeyHashCache.getStrValMap(key));
    }

    @Test
    public void putGetDeleteTest2() {

        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("111111", "{1:1,2:2}");
        stringStringMap.put("222222", "{2:1,2:2}");
        stringStringMap.put("333333", "{3:1,2:2}");
        stringStringMap.put("444444", "{4:1,2:2}");

        Key key = new Key().append("demoz.key").colon().append("hash.key.3");

        hkrKeyHashCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrKeyHashCache.getStrValMap(key));
        System.out.println(hkrKeyHashCache.keys(key));
        Set<String> sx = new HashSet<>();
        sx.add("333333");
        sx.add("444444");

        System.out.println("multiGet:" + hkrKeyHashCache.multiGet(key, sx));

        Assert.assertEquals(hkrKeyHashCache.hasKey(key, "222222"), true);
        Assert.assertEquals(hkrKeyHashCache.hasKey(key, "666666"), false);

        System.out.println("putIfAbsent:" + hkrKeyHashCache.putIfAbsent(key, "333333", "haha"));
        System.out.println("putIfAbsent:" + hkrKeyHashCache.putIfAbsent(key, "xxxKey", "haha"));
        System.out.println("putIfAbsent:" + hkrKeyHashCache.putIfAbsent(key, "dddddd", "haha"));

        System.out.println(hkrKeyHashCache.getStrValMap(key));

        System.out.println(hkrKeyHashCache.deleteHashKey(key, "333333"));

    }
}