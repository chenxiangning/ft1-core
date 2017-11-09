package com.reachauto.hkr.tennis.ft1.guavac;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 13:50
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 多重映射接口扩展映射，使得其键一次可被映射到多个值。 put 时 value为list val可重复,key不重复.put追加val
 */
public class MultimapTest {

    @Test
    public void democ() {
        Multimap multimap = getArrayListMultimap();
        List<String> lowerList = (List<String>) multimap.get("lower");

        System.out.println(lowerList.toString());
        lowerList.add("f");
        lowerList.remove("a");
        System.out.println(lowerList.toString());

        Map<String, Collection<String>> map = multimap.asMap();
        for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value = multimap.get(key);
            System.out.println(key + ":" + value);
        }

        Set<String> keys = multimap.keySet();
        for (String key : keys) {
            System.out.println(key);
        }

        Collection collection = multimap.values();
        collection.forEach(System.out::print);
        System.out.println();

        System.out.println("###########################################");
        System.out.println(multimap.containsEntry("lower", "a"));
        System.out.println(multimap.containsEntry("lower", "b"));
        System.out.println(multimap.containsKey("lower"));
        System.out.println(multimap.containsKey("xxx"));
        System.out.println(multimap.containsValue("A"));
        System.out.println(multimap.containsValue("Aa"));

        System.out.println(multimap.remove("lower", "b"));
        System.out.println(multimap.remove("lower", "xx"));


        System.out.println(multimap);
    }

    @Test
    public void demod() {
        System.out.println(getArrayListMultimap2());
        Multimap multimap = getArrayListMultimap2();

        multimap.putAll("aaa", Arrays.asList("1s", "2", "3", 4, 5, 6));

        System.out.println(multimap);
        System.out.println(multimap.replaceValues("aaa", Arrays.asList("666", "777")));
        System.out.println(multimap);
        multimap.putAll("aaa", Arrays.asList("000", "999", "666"));
        System.out.println(multimap);
        /**
             {ccc=[A], ddd=[A], lower=[a], upper=[A]}
             {aaa=[1s, 2, 3, 4, 5, 6], ccc=[A], ddd=[A], lower=[a], upper=[A]}
             [1s, 2, 3, 4, 5, 6]
             {aaa=[666, 777], ccc=[A], ddd=[A], lower=[a], upper=[A]}
             {aaa=[666, 777, 000, 999, 666], ccc=[A], ddd=[A], lower=[a], upper=[A]}
         */

    }
    @Test
    public void demoe() {
        System.out.println(getHashMultimap());
        Multimap multimap = getHashMultimap ();

        multimap.putAll("aaa", Arrays.asList("1s", "2", "3", 4, 5, 6));

        System.out.println(multimap);
        System.out.println(multimap.replaceValues("aaa", Arrays.asList("666", "777")));
        System.out.println(multimap);
        multimap.putAll("aaa", Arrays.asList("000", "999", "666"));
        System.out.println(multimap);
        /**
             {lower=[a, b, c, d, e], upper=[A, B, C, D]}
             {aaa=[1s, 2, 3, 4, 5, 6], lower=[a, b, c, d, e], upper=[A, B, C, D]}
             [1s, 2, 3, 4, 5, 6]
             {aaa=[666, 777], lower=[a, b, c, d, e], upper=[A, B, C, D]}
             {aaa=[000, 999, 666, 777], lower=[a, b, c, d, e], upper=[A, B, C, D]}
         */

    }

    private Multimap getArrayListMultimap() {
        Multimap multimap = ArrayListMultimap.create();
        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        return multimap;
    }


    private Multimap getArrayListMultimap2() {
        Multimap multimap = ArrayListMultimap.create();
        multimap.put("lower", "a");
        multimap.put("upper", "A");
        multimap.put("ddd", "A");
        multimap.put("ccc", "A");

        return multimap;
    }


    private Multimap getHashMultimap() {
        Multimap multimap = HashMultimap.create();
        multimap.put("lower", "a");
        multimap.put("upper", "A");
        multimap.put("ddd", "A");
        multimap.put("ccc", "A");

        return multimap;
    }

}