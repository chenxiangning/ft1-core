package com.reachauto.hkr.tennis.ft1.springscan.demo;

import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 22:27
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class DemoServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private DemoDao demoDao;

    @HkrCache(key = HkrCache.DEFAULT_PREFIX_KEY + "mn", expire = 99)
    public Object demoFindById(String id) {
        LOGGER.info("service 层调用 demoFindById({})", id);
        return demoDao.findById(id);
    }

    @HkrCache(key = HkrCache.DEFAULT_PREFIX_KEY + "mn", expire = 120)
    public Object demoFindByIdAndName(String id, String name) {
        LOGGER.info("service 层调用 demoFindByIdAndName({},{})", id, name);
        return demoDao.findByIdAndName(id, name);
    }

    @HkrCache(key = HkrCache.DEFAULT_PREFIX_KEY + "mn", expire = 120)
    public DemoBean getBean(int a, Integer b, String c, DemoBean d) {
        return demoDao.getB(a, b, c, d);

    }
}
