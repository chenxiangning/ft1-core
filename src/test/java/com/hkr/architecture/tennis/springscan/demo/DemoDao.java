package com.hkr.architecture.tennis.springscan.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 22:27
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
@Service
public class DemoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDao.class);

    public Object findById(String id) {
        LOGGER.info("查询数据库id={} 返回 12345", id);
        return "12345";
    }

    public Object findByIdAndName(String id, String name) {
        LOGGER.info("查询数据库id={} name={} 返回 6666666", id, name);
        return "6666666";
    }

    public DemoBean getB(int a, Integer b, String c, DemoBean d) {
        return new DemoBean("陈湘宁");
    }
}
