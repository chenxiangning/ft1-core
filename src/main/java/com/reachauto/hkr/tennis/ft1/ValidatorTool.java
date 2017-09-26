package com.reachauto.hkr.tennis.ft1;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 13:51
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public final class ValidatorTool {

    /**
     * Do not instantiate Validator.
     */
    private ValidatorTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }


    /**
     * 判断对象 <code>value</code> 是不是 null或者empty.
     * <p>
     * <h3>示例:</h3>
     * <p>
     * <blockquote>
     * <p>
     * <pre class="code">
     * <p>
     * <span style="color:green">// null</span>
     * Validator.isNullOrEmpty(null)                                            = true
     * <p>
     * <span style="color:green">//CharSequence</span>
     * Validator.isNullOrEmpty("")                                              = true
     * Validator.isNullOrEmpty("   ")                                           = true
     * <p>
     * Validator.isNullOrEmpty(new StringBuffer())                              = true
     * Validator.isNullOrEmpty(new StringBuffer(""))                            = true
     * Validator.isNullOrEmpty(new StringBuffer(" "))                           = true
     * <p>
     * Validator.isNullOrEmpty(new StringBuilder())                             = true
     * Validator.isNullOrEmpty(new StringBuilder(""))                           = true
     * Validator.isNullOrEmpty(new StringBuilder(" "))                          = true
     * <p>
     * <span style="color:green">//Collection</span>
     * Validator.isNullOrEmpty(new ArrayList{@code <String>}())                 = true
     * <p>
     * <span style="color:green">//Map</span>
     * Validator.isNullOrEmpty(new LinkedHashMap{@code <String, String>}())     = true
     * <p>
     * <span style="color:green">//Iterator</span>
     * Validator.isNullOrEmpty(new ArrayList{@code <String>}().iterator())      = true
     * <p>
     * <span style="color:green">//Enumeration</span>
     * Validator.isNullOrEmpty(toEnumeration(new ArrayList{@code <String>}()))  = true
     * <p>
     * <span style="color:green">//Array</span>
     * Validator.isNullOrEmpty(new String[] {})                                 = true
     * Validator.isNullOrEmpty(new Integer[][] {})                              = true
     * Validator.isNullOrEmpty(new User[] {})                                   = true
     * <p>
     * <span style="color:green">//Primitive Array</span>
     * Validator.isNullOrEmpty(new double[] {})                                 = true
     * Validator.isNullOrEmpty(new float[] {})                                  = true
     * Validator.isNullOrEmpty(new long[] {})                                   = true
     * Validator.isNullOrEmpty(new int[] {})                                    = true
     * Validator.isNullOrEmpty(new short[] {})                                  = true
     * Validator.isNullOrEmpty(new char[] {})                                   = true
     * Validator.isNullOrEmpty(new byte[] {})                                   = true
     * Validator.isNullOrEmpty(new boolean[] {})                                = true
     * </pre>
     * <p>
     * </blockquote>
     * <p>
     * <h3>对于empty的判断,使用以下逻辑:</h3>
     * <p>
     * <blockquote>
     * <ol>
     * <li>{@link CharSequence},支持子类有 {@link String},{@link StringBuffer},{@link StringBuilder},使用
     * {@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)};</li>
     * <li>{@link Collection},使用其 {@link Collection#isEmpty()};</li>
     * <li>{@link Map},使用其 {@link Map#isEmpty()};</li>
     * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()};</li>
     * <li>{@link Iterator},使用 {@link Iterator#hasNext()};</li>
     * <li><code>数组</code>{@link java.lang.Class#isArray()},判断 {@link Array#getLength(Object)} ==0</li>
     * </ol>
     * </blockquote>
     *
     * @param value 可以是{@link Collection},{@link Map},{@link Enumeration},{@link Iterator},{@link Iterable},{@link CharSequence},
     *              以及所有数组类型(包括原始类型数组)
     * @return 如果是null, 返回true<br>
     * 如果是empty也返回true<br>
     * 其他情况返回false<br>
     * @see org.apache.commons.collections4.CollectionUtils#sizeIsEmpty(Object)
     * @see org.apache.commons.lang3.StringUtils#isBlank(CharSequence)
     */
    public static boolean isNullOrEmpty(Object value) {
        if (null == value) {
            return true;
        }
        // 字符串
        if (value instanceof CharSequence) {
            return StringUtils.isBlank((CharSequence) value);
        }

        // collections 支持的类型
        if (isCollectionsSupportType(value)) {
            return CollectionUtils.sizeIsEmpty(value);
        }
        return false;
    }


    /**
     * 判断对象 <code>value</code> 是否不是null或者empty,调用 !{@link #isNullOrEmpty(Object)} 方法 .
     * <p>
     * <p>
     * 示例参考 {@link #isNullOrEmpty(Object)} 方法
     * </p>
     * <p>
     * <h3>对于empty的判断,使用以下逻辑:</h3>
     * <p>
     * <blockquote>
     * <ol>
     * <li>{@link CharSequence},支持子类有 {@link String},{@link StringBuffer},{@link StringBuilder},使用
     * {@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)};</li>
     * <li>{@link Collection},使用其 {@link Collection#isEmpty()};</li>
     * <li>{@link Map},使用其 {@link Map#isEmpty()};</li>
     * <li>{@link Enumeration},使用 {@link Enumeration#hasMoreElements()};</li>
     * <li>{@link Iterator},使用 {@link Iterator#hasNext()};</li>
     * <li><code>数组</code>{@link java.lang.Class#isArray()},判断 {@link Array#getLength(Object)} ==0</li>
     * </ol>
     * </blockquote>
     *
     * @param value 可以是{@link Collection},{@link Map},{@link Enumeration},{@link Iterator},{@link Iterable},{@link CharSequence}
     *              ,以及所有数组类型(包括原始类型数组)
     * @return 如果是null, 返回false<br>
     * 如果是空也返回false<br>
     * 其他情况返回true<br>
     * @see #isNullOrEmpty(Object)
     */
    public static boolean isNotNullOrEmpty(Object value) {
        return !isNullOrEmpty(value);
    }

    /**
     * Checks if is collections support type.
     *
     * @param value the value
     * @return true, if checks if is collections support type
     * @since 1.5.2
     */
    private static boolean isCollectionsSupportType(Object value) {
        // 集合或者map
        boolean isCollectionOrMap = value instanceof Collection || value instanceof Map;

        // 枚举 或者是 Iterator迭代器
        boolean isEnumerationOrIterator = value instanceof Enumeration || value instanceof Iterator;

        return isCollectionOrMap//集合或者map
                || isEnumerationOrIterator//枚举 或者是 Iterator迭代器
                || value.getClass().isArray()//判断数组
                ;
    }

}
