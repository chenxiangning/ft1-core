package com.reachauto.hkr.tennis.ft1.springscan.demo;

import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache;

public interface DemoSer {

    @HkrCache(model = "mn", expire = 33)
    Object demoFindById(String id);
}
