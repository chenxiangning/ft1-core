package com.rental.hkr.tennis.notscan;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/29 20:17
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
public class GoogleGuava {

    @Test
    public void baseUtilTest() {
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull("  "));
        System.out.println(Strings.emptyToNull("ss"));

        Preconditions.checkArgument(1< 11, "Argument was %s but expected nonnegative", 3);
    }
}
