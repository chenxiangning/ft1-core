package com.reachauto.hkr.tennis.ft1.springscan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 23:23
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@RestController
public class DemoController {

    @Autowired
    private DemoServiceImpl demoService;
    @Autowired
    private DemoSer demoSer;

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {

        return "hello world" + demoService.demoFindByIdAndName("111", "222sss")
                + demoService.demoFindById("sdsd");
    }

    @RequestMapping("/")
    @ResponseBody
    public String g() {
        demoService.demoFindByIdAndName("111", "222sss");
        demoService.demoFindById("sdsd");
        demoService.getBean(1, 2, "3", new DemoBean("demo"));
        demoService.getBean(1, 2, "3", new com.reachauto.hkr.tennis.ft1.springscan.cache.DemoBean("22222"));
        return "";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del() {
        demoService.del("xxxxxx");
        return "";
    }
    @RequestMapping("/i")
    @ResponseBody
    public String i() {
        demoSer.demoFindById("123123");
        return "123";
    }
}
