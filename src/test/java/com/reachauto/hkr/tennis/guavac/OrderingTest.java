package com.reachauto.hkr.tennis.guavac;

import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 10:27
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: Ordering(排序)可以被看作是一个丰富的比较具有增强功能的链接，多个实用方法，多类型排序功能等
 */
public class OrderingTest {

    @Test
    public void demo1() {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        //返回使用值的自然顺序排序序列化
        Ordering ordering = Ordering.natural();
        System.out.println("numbers :" + numbers);

        Collections.sort(numbers, ordering);
        System.out.println("Sorted list:");
        System.out.println(numbers);

        System.out.println("List is sorted:" + ordering.isOrdered(numbers));
        System.out.println("max:" + ordering.max(numbers));
        System.out.println("min:" + ordering.min(numbers));

        Collections.sort(numbers, ordering.reversed());
        System.out.println("Reversed:" + numbers);
        System.out.println("List is sorted:" + ordering.isOrdered(numbers));

        numbers.add(null);
        System.out.println(numbers);

        Collections.sort(numbers,ordering.nullsFirst());
        System.out.println(numbers);
        Collections.sort(numbers,ordering.nullsLast());
        System.out.println(numbers);
    }
}