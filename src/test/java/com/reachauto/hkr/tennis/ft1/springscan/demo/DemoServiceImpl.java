package com.reachauto.hkr.tennis.ft1.springscan.demo;

import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCache;
import com.reachauto.hkr.tennis.ft1.springscan.cache.annotation.HkrCacheDel;
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

    @HkrCache(model = "mn", expire = 33)
    public Object demoFindById(String id) {
        LOGGER.info("service 层调用 demoFindById({})", id);
        return demoDao.findById(id);
    }

    @HkrCache(model = "mn", expire = 33)
    public Object demoFindByIdAndName(String id, String name) {
        LOGGER.info("service 层调用 demoFindByIdAndName({},{})", id, name);
        return demoDao.findByIdAndName(id, name);
    }

    @HkrCache(model = "mn", expire = 33)
    public DemoBean getBean(int a, Integer b, String c, DemoBean d) {
        return demoDao.getB(a, b, c, d);

    }

    @HkrCache(model = "mn", expire = 33)
    public com.reachauto.hkr.tennis.ft1.springscan.cache.DemoBean getBean(int a, Integer b, String c, com.reachauto.hkr.tennis.ft1.springscan.cache.DemoBean d) {
        return new com.reachauto.hkr.tennis.ft1.springscan.cache.DemoBean("陈湘宁1");

    }

    @HkrCacheDel(model = "mn", delKey = {"*"},isModelAll = true)
    public void del(String xx) {
        System.out.println("清理缓存");
    }

    @HkrCacheDel(model = "mn", delKey = {"*"})
    public void del2(String xx) {
        System.out.println("清理缓存");
    }
}
