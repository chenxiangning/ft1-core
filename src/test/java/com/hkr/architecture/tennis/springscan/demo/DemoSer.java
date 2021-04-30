package com.hkr.architecture.tennis.springscan.demo;

import com.hkr.architecture.tennis.springscan.cache.annotation.HkrCache;

public interface DemoSer {

    @HkrCache(model = "mn", expire = 33)
    Object demoFindById(String id);
}
