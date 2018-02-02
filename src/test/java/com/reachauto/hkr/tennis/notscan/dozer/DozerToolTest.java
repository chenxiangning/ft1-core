package com.reachauto.hkr.tennis.notscan.dozer;

import com.reachauto.hkr.tennis.notscan.gson.Dage;
import com.reachauto.hkr.tennis.notscan.gson.Gbean;
import com.reachauto.hkr.tennis.notscan.gson.GsonTool;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class DozerToolTest {

    String jsonx = "{\"kkkkk\":null,\"name\":\"cxn\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":\"Sep 21, 2017 8:09:53 PM\",\"delFlag\":0,\"createAt\":1505976480000,\"curtime\":1505976606634}";

    Map map = new HashMap();
    Dage dage = new Dage();
    Gbean gbean = new Gbean();
    Gbean gbean2 = new Gbean();
    Tbean tbean = new Tbean();

    @Before
    public void init() {
        map.put("name", "cxn");
        map.put("age", "3");
        map.put("time", new Date());
        map.put("xx", null);
        map.put("code", 1001);
        map.put("fen", 88.32);
        map.put("curtime", System.currentTimeMillis());

        gbean = GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Gbean.class);
        gbean2 = GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Gbean.class);
        tbean = GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Tbean.class);

        gbean2.setName("大侠");
        dage.setMz("大哥");
        dage.setGbeans(Arrays.asList(gbean, gbean2));

        System.out.println(dage);
    }


    @Test
    public void deepCopy() throws Exception {
//        Dage dage2 = DozerTool.deepCopy(dage, Dage.class);
//        Gbean gbean3 = DozerTool.deepCopy(gbean2, Gbean.class);
//        System.out.println(gbean3);

        for (int i = 0; i < 10; i++) {
            System.out.println(DozerTool.deepCopy(gbean2, Gbean.class));

        }

    }


//    @Test
//    public void deepCopy2() throws Exception {
//        List<Tbean> tbeans = Arrays.asList(GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Tbean.class),
//                GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Tbean.class));
//
//        List<TbeanVO> tbeanVOS = DozerTool.deepCopy(tbeans, List.class);
//
//        System.out.println(tbeanVOS);
//    }
//
//    @Test
//    public void deepCopyList() throws Exception {
//        List<Tbean> tbeans = Arrays.asList(GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Tbean.class),
//                GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Tbean.class));
//
//        List<TbeanVO> tbeanVOS = DozerTool.deepListCopy(tbeans, TbeanVO.class);
//
//        System.out.println(tbeanVOS);
//    }

}