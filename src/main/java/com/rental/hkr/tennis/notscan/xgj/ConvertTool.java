package com.rental.hkr.tennis.notscan.xgj;

import com.rental.hkr.tennis.TennisToolException;
import com.rental.hkr.tennis.ValidatorTool;
import com.rental.hkr.tennis.notscan.gson.GsonTool;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2018/1/28 18:32
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
public class ConvertTool {
    /**
     * Do not instantiate RandomTool.
     */
    private ConvertTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 利用json转换 mapTobean
     *
     * @param map 1
     * @param c   class
     * @param <T> 泛型
     * @return T
     */
    public static <T> T mapToBean(Map map, Class<T> c) {
        return GsonTool.jsonToBean(GsonTool.objectToAllFieldNullJson(map), c);
    }


    /**
     * beanToMap
     *
     * @param bean 带转换bean
     * @param <T>  泛型
     * @return map
     */
    public static <T> Map beanToMap(T bean) {
        if (ValidatorTool.isNullOrEmpty(bean)) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            throw new TennisToolException("beanToMap error!", e);
        }
        return map;
    }
}
