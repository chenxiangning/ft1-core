package com.reachauto.hkr.tennis.springscan.cache;

import com.reachauto.hkr.tennis.AbstractJUnit;
import com.reachauto.hkr.tennis.springscan.cache.redis.Key;
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

    @Autowired
    @Qualifier(value = "hkrRedisDBShiroHashStrCache")
    private HkrKeyHashCache hkrRedisDBShiroHashStrCache;


    @Test
    public void xx() {
        System.out.println(hkrKeyValCache);
        System.out.println(hkrKeyHashCache);
        System.out.println(new Key().append("xxx").colon().append("sdsd").getTrueKey());
        System.out.println(Key.build("a:b").colon().append("xxx").colon().append("sdsd").getTrueKey());
        hkrKeyValCache.set(Key.buildAndNew("quota"), "10");
        hkrKeyValCache.set(Key.buildAndNew("limit"), "1");
    }

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
        stringStringMap.put("/hkr-am/api/v1/log_operators*", "rest[log_operators:list]");

        Key key = new Key().append("auth").colon().append("table");

        hkrRedisDBShiroHashStrCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrRedisDBShiroHashStrCache.getStrValMap(key));


        System.out.println(hkrRedisDBShiroHashStrCache.deleteHashKey(new Key().append("role").colon().append("1"), "log_operators:list:read", "xlog_operators:list:read"));
        Map<String, String> xxx2 = new HashMap<>();
        xxx2.put("log_operators:list:read", "666111");

        Key key22 = new Key().append("role").colon().append("1");

        hkrRedisDBShiroHashStrCache.setStrValMap(key22, xxx2);

        System.out.println(hkrRedisDBShiroHashStrCache.getStrValMap(key22));


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



    @Test
    public void xianliu_jshujujiegou() {

        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("qps#GET#/hkr-am/api/v1/log_operators", "200");
        stringStringMap.put("limit#GET#/hkr-am/api/v1/log_operators", "1");
        stringStringMap.put("qps#GET#/hkr-bk/api/v1/log_operators", "200");
        stringStringMap.put("limit#GET#/hkr-bk/api/v1/log_operators", "1");

        Key key = Key.buildAndNew("ratelimit:individual");

        hkrKeyHashCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrKeyHashCache.getStrValMap(key));
        System.out.println(hkrKeyHashCache.keys(key));

    }

    @Test
    public void xianliu_jshujujiegou2() {

        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("qps#GET", "200");
        stringStringMap.put("limit#GET", "1");
        stringStringMap.put("qps#POST", "200");
        stringStringMap.put("limit#POST", "1");
        stringStringMap.put("qps#PUT", "200");
        stringStringMap.put("limit#PUT", "1");

        Key key = Key.buildAndNew("ratelimit:global");

        hkrKeyHashCache.setStrValMap(key, stringStringMap);

        System.out.println(hkrKeyHashCache.getStrValMap(key));
        System.out.println(hkrKeyHashCache.keys(key));

    }


    @Test
    public void xianliu_shishi() {

        hkrKeyValCache.set(Key.buildAndNew("ratelimit:individual:qps-realtime#GET#/hkr-am/api/v1/log_operators"), "13");
        hkrKeyValCache.set(Key.buildAndNew("ratelimit:individual:limit-window#GET#/hkr-am/api/v1/log_operators"), "1");

        System.out.println(hkrKeyValCache.get(Key.buildAndNew("ratelimit:individual:qps-realtime#GET#/hkr-am/api/v1/log_operators")));
        System.out.println(hkrKeyValCache.get(Key.buildAndNew("ratelimit:individual:limit-window#GET#/hkr-am/api/v1/log_operators")));
    }

}