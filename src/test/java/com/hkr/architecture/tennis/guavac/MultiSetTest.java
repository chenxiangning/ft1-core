package com.hkr.architecture.tennis.guavac;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 13:35
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
public class MultiSetTest {

    @Test
    public void democ() {
        Multiset<String> strings = ConcurrentHashMultiset.create();
        strings.add("cxn");
        strings.add("a");
        strings.add("v");
        strings.add("v");
        strings.add("v");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        strings.add("cxn");

        strings.forEach(s -> System.out.print(s + " "));
        print("", "");
        print("strings.count(\"cxn\")", strings.count("cxn"));
        print("strings.count(\"xx\")", strings.count("xx"));

        print("strings.size()", strings.size());

//        Multiset.Entry<String> entry
        strings.entrySet().forEach(stringEntry -> {
            System.out.println("Element: " + stringEntry.getElement() + ", Occurrence(s): " + stringEntry.getCount());
        });

        strings.remove("v",2);
        strings.remove("cxn");

        System.out.println(strings.count("v"));
        strings.entrySet().forEach(stringEntry -> {
            System.out.println("Element: " + stringEntry.getElement() + ", Occurrence(s): " + stringEntry.getCount());
        });

    }

    private void print(String s, Object xxx) {
        System.out.println(s + " " + xxx);
    }
}
