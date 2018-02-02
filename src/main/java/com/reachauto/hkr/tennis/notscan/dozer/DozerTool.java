package com.reachauto.hkr.tennis.notscan.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2018-02-02 16:54
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
public class DozerTool {

    private static Mapper mapper = new DozerBeanMapper();


    /**
     * Do not instantiate DozerTool.
     */
    private DozerTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }


    public static <T> T deepCopy(Object source, Class<T> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public static <T> List<T> deepListCopy(List<?> source, Class<T> destinationClass) {
        return source.stream().map(o -> deepCopy(o, destinationClass)).collect(Collectors.toList());
    }


}