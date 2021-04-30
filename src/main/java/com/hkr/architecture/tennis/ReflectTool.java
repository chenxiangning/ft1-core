package com.hkr.architecture.tennis;


import com.hkr.architecture.tennis.notscan.cache.SimpleCache;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author Looly
 * @since 3.0.9
 */
public class ReflectTool {

    /**
     * 构造对象缓存
     */
    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();
    /**
     * 字段缓存
     */
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();
    /**
     * 方法缓存
     */
    private static final SimpleCache<Class<?>, Method[]> METHODS_CACHE = new SimpleCache<>();

    /**
     * 获得一个类中所有字段列表
     *
     * @param <T>       构造的对象类型
     * @param beanClass 类
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     */
    @SuppressWarnings("unchecked")
    public static <T> Constructor<T>[] getConstructors(Class<T> beanClass) throws SecurityException {
        Assert.notNull(beanClass, "is null");
        Constructor<?>[] constructors = CONSTRUCTORS_CACHE.get(beanClass);
        if (null != constructors) {
            return (Constructor<T>[]) constructors;
        }

        constructors = getConstructorsDirectly(beanClass);
        return (Constructor<T>[]) CONSTRUCTORS_CACHE.put(beanClass, constructors);
    }

    /**
     * 获得一个类中所有字段列表，直接反射获取，无缓存
     *
     * @param beanClass 类
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     */
    public static Constructor<?>[] getConstructorsDirectly(Class<?> beanClass) throws SecurityException {
        Assert.notNull(beanClass, "is null");
        return beanClass.getDeclaredConstructors();
    }

    // --------------------------------------------------------------------------------------------------------- Field


    /**
     * 获取字段值
     *
     * @param obj   对象
     * @param field 字段
     * @return 字段值
     * @throws TtServerException 包装IllegalAccessException异常
     */
    public static Object getFieldValue(Object obj, Field field) throws TtServerException {
        if (null == obj || null == field) {
            return null;
        }
        field.setAccessible(true);
        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            throw new TtServerException("IllegalAccess for " + obj.getClass() + "." + field.getName(), e);
        }
        return result;
    }


    /**
     * 设置字段值
     *
     * @param obj   对象
     * @param field 字段
     * @param value 值，值类型必须与字段类型匹配，不会自动转换对象类型
     * @throws TtServerException HkrServerException 包装IllegalAccessException异常
     */
    public static void setFieldValue(Object obj, Field field, Object value) throws TtServerException {
        Assert.notNull(obj, "obj is null");
        Assert.notNull(field, "field is null");
        field.setAccessible(true);

        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new TtServerException("IllegalAccess for " + obj.getClass() + "." + field.getName(), e);
        }
    }


    /**
     * 是否为equals方法
     *
     * @param method 方法
     * @return 是否为equals方法
     */
    public static boolean isEqualsMethod(Method method) {
        if (method == null || false == method.getName().equals("equals")) {
            return false;
        }
        final Class<?>[] paramTypes = method.getParameterTypes();
        return (1 == paramTypes.length && paramTypes[0] == Object.class);
    }

    /**
     * 是否为hashCode方法
     *
     * @param method 方法
     * @return 是否为hashCode方法
     */
    public static boolean isHashCodeMethod(Method method) {
        return (method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0);
    }

    /**
     * 是否为toString方法
     *
     * @param method 方法
     * @return 是否为toString方法
     */
    public static boolean isToStringMethod(Method method) {
        return (method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0);
    }


    /**
     * 实例化对象
     *
     * @param <T>   对象类型
     * @param clazz 类名
     * @return 对象
     * @throws TtServerException 包装各类异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazz) throws TtServerException {
        try {
            return (T) Class.forName(clazz).newInstance();
        } catch (Exception e) {
            throw new TtServerException("Instance: " + clazz, e);
        }
    }


}
