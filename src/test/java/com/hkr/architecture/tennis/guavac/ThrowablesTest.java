package com.hkr.architecture.tennis.guavac;

import com.google.common.base.Throwables;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 11:03
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
public class ThrowablesTest {
    @Test
    public void democ() {
        showcaseThrowables1();
    }

    public void showcaseThrowables1(){
        try {
            int[] data = {1,2,3};
            getValue(data, 4);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=======================================");
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    public double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }
}
