package com.hkr.architecture.tennis.notscan.gson;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GsonToolTest {

    String jsonx = "{\"kkkkk\":null,\"name\":\"cxn\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":\"Sep 21, 2017 8:09:53 PM\",\"delFlag\":0,\"createAt\":1505976480000,\"curtime\":1505976606634}";

    Map map = new HashMap();
    Gbean gbean = new Gbean();

    @Before
    public void init() {
        map.put("name", "cxn");
        map.put("age", "3");
        map.put("time", new Date());
        map.put("xx", null);
        map.put("code", 1001);
        map.put("fen", 88.32);

        gbean = GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Gbean.class);
    }

    @Test
    public void init2() {
        System.out.println(gbean);

    }

    @Test
    public void objectToJsonDateSerializer() {
        String j = GsonTool.objectToJsonDateSerializer(gbean, "yyyy-MM-dd : HH:mm:ss");
        System.out.println("objectToJsonDateSerializer " + j);
    }

    @Test
    public void objectToAllFieldEmptyJson() {
        System.out.println("objectToAllFieldEmptyJson " + GsonTool.objectToAllFieldEmptyJson(gbean));
    }


    @Test
    public void objectToAllFieldNullJson() {
        System.out.println("objectToAllFieldNullJson " + GsonTool.objectToAllFieldNullJson(gbean));
    }

    @Test
    public void objectToNotNullJson() {
        System.out.println("objectToNotNullJson " + GsonTool.objectToNotNullJson(gbean));
    }

    @Test
    public void objectToExposeJson() {
        System.out.println("objectToExposeJson " + GsonTool.objectToExposeJson(gbean));
    }

    @Test
    public void jsonToBean() {
        System.out.println("@@ " + GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Gbean.class));
        System.out.println("@@ " + GsonTool.jsonToBean(GsonTool.getGsonToBeanNullToEmpty(), jsonx, Gbean.class));
        System.out.println("@@ " + GsonTool.jsonToBean(GsonTool.getBeanToGsonNullToEmpty(), jsonx, Gbean.class));
        System.out.println("@@ " + GsonTool.jsonToBean(GsonTool.getGsonExpose(), jsonx, Gbean.class));
        System.out.println("@@ " + GsonTool.jsonToBean(GsonTool.getGsonNotNull(), jsonx, Gbean.class));
    }

    @Test
    public void jsonToList() {
        List<Gbean> gbeanList = Arrays.asList(gbean);
        String listJson = GsonTool.objectToAllFieldEmptyJson(gbeanList);
        System.out.println("json:" + listJson);
        String listJson2 = GsonTool.objectToAllFieldEmptyJson(gbean);
        System.out.println("json2:" + listJson2);

        System.out.println("%% " + GsonTool.jsonToList(GsonTool.getGsonToBeanNullToEmpty(), listJson));
    }

    @Test
    public void defaultc() {
        Gbean gbean = GsonTool.jsonToBean(jsonx, Gbean.class);

        System.out.println(gbean);
    }


}
