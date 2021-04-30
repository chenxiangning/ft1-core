package com.rental.hkr.tennis.notscan.gson;

import com.google.gson.reflect.TypeToken;
import com.rental.hkr.tennis.notscan.xgj.ConvertTool;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GsonToolListTest {

    String jsonx = "{\"kkkkk\":null,\"name\":\"cxn\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":\"Sep 21, 2017 8:09:53 PM\",\"delFlag\":0,\"createAt\":1505976480000,\"curtime\":1505976606634}";

    Map map = new HashMap();
    Dage dage = new Dage();
    Gbean gbean = new Gbean();
    Gbean gbean2 = new Gbean();

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
        gbean2.setName("大侠");
        dage.setMz("大哥");
        dage.setGbeans(Arrays.asList(gbean, gbean2));

        System.out.println(GsonTool.objectToAllFieldNullJson(Arrays.asList(gbean, gbean2)));
        System.out.println();
    }


    @Test
    public void objectToJsonDateSerializer() {
        String dajson = GsonTool.objectToAllFieldEmptyJson(dage);
        System.out.println(dajson);

        Dage dage = GsonTool.jsonToBean(GsonTool.getGsonToBeanNullToEmpty(), dajson, Dage.class);

        System.out.println(dage);
    }

    @Test
    public void objectToAllFieldEmptyJson() {
        System.out.println("objectToAllFieldEmptyJson " + GsonTool.objectToAllFieldEmptyJson(gbean));
    }

    @Test
    public void listJson() {
        String listjson = "[{\"ooooxxx\":null,\"kkkkk\":null,\"name\":\"cxn\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":1505995793000,\"createAt\":1505976480000,\"curtime\":1505976606634},{\"ooooxxx\":null,\"kkkkk\":null,\"name\":\"大侠\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":1505995793000,\"createAt\":1505976480000,\"curtime\":1505976606634}]";

        List<Gbean> gbeans = GsonTool.jsonToList(listjson, new TypeToken<List<Gbean>>() {
        }.getType());

        System.out.println(gbeans);
    }

    @Test
    public void map() {
        Gbean gbean = ConvertTool.mapToBean(map, Gbean.class);

        System.out.println(gbean);

        System.out.println(ConvertTool.beanToMap(gbean));
    }
}
