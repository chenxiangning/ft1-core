package com.tennis.ft1.notscan.gson;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GsonToolTest {
    @Test
    public void objectToJson() throws Exception {
        Map map = new HashMap();

        map.put("name", "cxn");
        map.put("age", "3");
        map.put("time", new Date());
        map.put("xx", null);
        map.put("code", 1001);
        map.put("fen", 88.32);

        System.out.println(GsonTool.objectToAllFieldJson(map));
        String json = GsonTool.objectToAllFieldJson(map);

        System.out.println(GsonTool.objectToNotNullJson(map));
        System.out.println(GsonTool.objectToExposeJson(map));
        System.out.println(GsonTool.objectToJsonDateSerializer(map, "yyyy-MM-dd"));

        Gbean gbean = GsonTool.jsonToBean(GsonTool.getGsonNotNull(), json, Gbean.class);

        System.out.println(gbean);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(GsonTool.objectToNotNullJson(gbean));
        System.out.println(GsonTool.objectToExposeJson(gbean));
        System.out.println(GsonTool.objectToJsonDateSerializer(gbean, "yyyy-MM-dd HH:mm:ss"));


        System.out.println(GsonTool.objectToAllFieldJson(gbean));
        System.out.println(GsonTool.objectToAllFieldJson222(gbean));


    }

}