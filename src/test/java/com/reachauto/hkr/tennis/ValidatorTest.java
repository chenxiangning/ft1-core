package com.reachauto.hkr.tennis;

import com.reachauto.hkr.tennis.ValidatorTool;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ValidatorTest {
    @Test
    public void isNullOrEmpty() throws Exception {

        // null
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(null), true);


        //CharSequence
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(""), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty("   "), true);


        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuffer()), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuffer("")), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuffer(" ")), true);


        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuilder()), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuilder("")), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new StringBuilder(" ")), true);


        //Collection
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new ArrayList<String>()), true);


        //Map
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new LinkedHashMap<String, String>()), true);


        //Iterator
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new ArrayList<String>().iterator()), true);

        //Array
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new String[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new Integer[][]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new SecurityProperties.User[]{}), true);


        //Primitive Array
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new double[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new float[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new long[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new int[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new short[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new char[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new byte[]{}), true);
        Assert.assertEquals(ValidatorTool.isNullOrEmpty(new boolean[]{}), true);

    }

    @Test
    public void isNotNullOrEmpty() throws Exception {

        // null
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(null), false);


        //CharSequence
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(""), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty("   "), false);


        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuffer()), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuffer("")), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuffer(" ")), false);


        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuilder()), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuilder("")), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new StringBuilder(" ")), false);


        //Collection
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new ArrayList<String>()), false);


        //Map
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new LinkedHashMap<String, String>()), false);


        //Iterator
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new ArrayList<String>().iterator()), false);

        //Array
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new String[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new Integer[][]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new SecurityProperties.User[]{}), false);


        //Primitive Array
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new double[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new float[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new long[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new int[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new short[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new char[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new byte[]{}), false);
        Assert.assertEquals(ValidatorTool.isNotNullOrEmpty(new boolean[]{}), false);


    }

}