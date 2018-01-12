package com.reachauto.hkr.tennis.springscan.demo;

import com.reachauto.hkr.exception.HkrBusinessException;
import com.reachauto.hkr.tennis.springscan.handler.LoginContextTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        demoService.getBean(1, 2, "3", new com.reachauto.hkr.tennis.springscan.cache.DemoBean("22222"));
        return "";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del() {
        demoService.del("xxxxxx");
        demoService.del2("xxxxxx");
        return "";
    }

    @RequestMapping("/i")
    @ResponseBody
    public String i() {
        demoSer.demoFindById("123123");
        return LoginContextTool.get().toString();
    }

    @RequestMapping("/exception")
    @ResponseBody
    public String ec() {
        throw new HkrBusinessException(123, "啦啦");
    }

    @RequestMapping("/exception2")
    @ResponseBody
    public String ec2() {
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        fixedThreadPool.execute(() -> {
            System.out.println(12345);
            throw new HkrBusinessException(1122, "123asdasd");
        });

        return "";
    }

    @RequestMapping(value = "ppp", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String ppp(@RequestBody @Valid PppParam pppParam) {


        return "ppp";
    }

    @RequestMapping(value = "ooo_put", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String ooo_put(@RequestBody @Valid PppParam pppParam) {
        System.out.println("ooo_put");

        return "ooo_put";
    }

    @RequestMapping(value = "ooo_del", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String ooo_del(@RequestBody @Valid PppParam pppParam) {
        System.out.println("ooo_del");
        System.out.println(LoginContextTool.get().toString());
        System.out.println(Thread.currentThread().getName());

        return "ooo_del";
    }
}
