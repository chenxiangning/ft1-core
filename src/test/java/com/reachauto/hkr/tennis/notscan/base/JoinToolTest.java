package com.reachauto.hkr.tennis.notscan.base;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JoinToolTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void myTest() throws Exception {
        Assert.assertEquals("1,5,7", JoinTool.on(",").join(Arrays.asList(1, 5, 7)));
        Assert.assertEquals("a,b,c", JoinTool.on(",").join(new String[]{"a", "b", "c"}));
//        JoinTool.on(",").join(new String[]{"a", null, "c"});
        Assert.assertEquals("dd#ss", JoinTool.on("#").skipNulls().join(new String[]{null, null, "dd", "ss"}));
        Assert.assertEquals("替换#替换#dd#ss", JoinTool.on("#").useForNull("替换").join(new String[]{null, null, "dd", "ss"}));
        Assert.assertEquals("头ddss", JoinTool.on("").appendTo(new StringBuilder("头"), new String[]{"dd", "ss"}).toString());
        System.out.println(123);
    }

    @Test
    public void myTest2() throws Exception {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "b");
        Assert.assertEquals("1->a|2->b", JoinTool.on("|").withKeyValueSeparator("->").join(map));


    }

}