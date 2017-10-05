package com.reachauto.hkr.tennis.ft1.notscan.base;

import org.junit.Test;

public class SplitToolTest {

    @Test
    public void demo01() throws Exception {
        System.out.println(SplitTool.on(' ').split("1 2 3")); //[1, 2, 3]
        System.out.println(SplitTool.onPattern("\\s+").split("1    2 3")); //["1", "2", "3"]
        System.out.println(SplitTool.fixedLength(2).split("123456789ABCD")); //[12, 34, 56, 78, 9A, BC, D]

        System.out.println(SplitTool.on("#").withKeyValueSeparator(":").split("1:2#3:4"));
    }

}