package com.tennis.ft1.springscan.cache;

import com.tennis.ft1.springscan.cache.redis.Key;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/20 22:18
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public interface HkrKeyHashCache {

    Map<String, String> getStrValMap(Key key);

    Map<String, Object> getObjValMap(Key key);

    String setStrValMap(Key key, Map<String, String> value);

    String setObjValMap(Key key, Map<String, Object> value);
}
