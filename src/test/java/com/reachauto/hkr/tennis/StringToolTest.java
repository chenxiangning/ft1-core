package com.reachauto.hkr.tennis;

import com.reachauto.hkr.tennis.StringTool;
import org.junit.Test;

public class StringToolTest {
    @Test
    public void upperFirst() throws Exception {
        String demo = "demo";
        String nihao = "NIhao";
        String x = "lngX";
        String c = "A";

        System.out.println(StringTool.upperFirst(demo));

        System.out.println(StringTool.upperFirst(nihao));

        System.out.println(StringTool.upperFirst(x));

        System.out.println(StringTool.upperFirst(c));
    }

    @Test
    public void lowerFirst() throws Exception {
        String demo = "demo";
        String nihao = "NIhao";
        String x = "lngX";
        String c = "A";

        System.out.println(StringTool.lowerFirst(demo));

        System.out.println(StringTool.lowerFirst(nihao));
        System.out.println(StringTool.lowerFirst(x));
        System.out.println(StringTool.lowerFirst(c));
    }

    @Test
    public void removePreAndLowerFirst() throws Exception {
        String demo = "demo";
        String nihao = "setDemo";

        System.out.println(StringTool.removePreAndLowerFirst(demo, 2));
        System.out.println(StringTool.removePreAndLowerFirst(nihao, 3));

    }
    @Test
    public void upperFirstAndAddPre() throws Exception {
        String ggg = "get";
        String sss = "set";

        System.out.println(StringTool.upperFirstAndAddPre(ggg, "age"));
        System.out.println(StringTool.upperFirstAndAddPre(sss, "age"));

    }

}