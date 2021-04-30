package com.hkr.architecture.tennis;

import org.junit.Assert;
import org.junit.Test;

public class RandomToolTest {
    @Test
    public void createRandomWithLength() throws Exception {
        System.out.println(RandomTool.createRandomWithLength(1));
        System.out.println(RandomTool.createRandomWithLength(2));
        System.out.println(RandomTool.createRandomWithLength(3));
        System.out.println(RandomTool.createRandomWithLength(4));
        System.out.println(RandomTool.createRandomWithLength(5));

        /**
         9
         46
         201
         9124
         14132
         */

        for (int i = 0; i < 10000; i++) {
            long r = RandomTool.createRandomWithLength(5);
            if (r < 10000 || r > 100000) {
                System.out.println(r);
                Assert.assertEquals(true, false);
            }
        }
    }

    @Test
    public void createRandomFromString() throws Exception {
        System.out.println(RandomTool.createRandomFromString("chenxiangning", 4));
        System.out.println(RandomTool.createRandomFromString("chenxiangning", 4));
        System.out.println(RandomTool.createRandomFromString("chenxiangning", 4));
        System.out.println(RandomTool.createRandomFromString("chenxiangning", 4));
        /*
        ngnn
        ignh
        xnxn
        nihg
         */
    }

}
