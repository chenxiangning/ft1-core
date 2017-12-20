package com.reachauto.hkr.tennis.springscan.demo;

import com.reachauto.hkr.tennis.springscan.cache.annotation.HkrCache;

public interface DemoSer {

    @HkrCache(model = "mn", expire = 33)
    Object demoFindById(String id);
}
