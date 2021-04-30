package com.hkr.architecture.tennis.springscan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-09-28 16:06
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
@Service
public class DemoSerImpl implements DemoSer {
    @Autowired
    private DemoDao demoDao;

    @Override
    public Object demoFindById(String id) {
        System.out.println("接口打印");
        return demoDao.findById(id);
    }
}
