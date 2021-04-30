package com.rental.hkr.tennis.springscan.demo;

import com.rental.hkr.tennis.springscan.cache.annotation.HkrCache;

public interface DemoSer {

    @HkrCache(model = "mn", expire = 33)
    Object demoFindById(String id);
}
