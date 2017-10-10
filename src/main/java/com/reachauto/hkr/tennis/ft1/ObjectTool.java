package com.reachauto.hkr.tennis.ft1;

import org.apache.commons.lang3.Validate;

import static com.reachauto.hkr.tennis.ft1.ValidatorTool.isNotNullOrEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 14:51
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public final class ObjectTool {

    /**
     * Do not instantiate ObjectTool.
     */
    private ObjectTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 如果 <code>object</code> 是null或者empty,返回默认值 <code>defaultValue</code>.
     * <h3>示例:</h3>
     * <blockquote>
     * <pre>
     * ObjectTool.defaultIfNullOrEmpty(null, null)      = null
     * ObjectTool.defaultIfNullOrEmpty(null, "")        = ""
     * ObjectTool.defaultIfNullOrEmpty(null, "zz")      = "zz"
     * ObjectTool.defaultIfNullOrEmpty("abc", *)        = "abc"
     * ObjectTool.defaultIfNullOrEmpty(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     * 
     * </blockquote>
     *
     * @param <T>          the type of the object
     * @param object       the {@code Object} to test, 可以是 {@code null} or empty
     * @param defaultValue the default value to return, 可以是 {@code null} or empty
     * @return 如果 <code>object</code> 是null或者empty,返回 <code>defaultValue</code>,否则返回 <code>object</code>
     * @see org.apache.commons.lang3.ObjectUtils#defaultIfNull(Object, Object)
     * @see org.apache.commons.collections4.ListUtils#defaultIfNull(java.util.List, java.util.List)
     * @since 1.7.2
     */
    public static <T> T defaultIfNullOrEmpty(final T object, final T defaultValue) {
        return isNotNullOrEmpty(object) ? object : defaultValue;
    }

    /**
     * 判断指定的对象 <code>object</code>是否是数组.
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>支持判断原始类型数组 <code>primitive</code> 和包装类型数组</li>
     * </ol>
     * </blockquote>
     * <h3>示例:</h3>
     * <blockquote>
     * <pre class="code">
     * int[] i = {};
     * ObjectTool.isArray(i);                       =true
     * ObjectTool.isArray(new int[] { 1, 2, 3 });   =true
     * ObjectTool.isArray(new Integer[0]);          =true
     * ObjectTool.isArray(new String[0]);           =true
     * </pre>
     * </blockquote>
     * <h3><code>instanceof</code>和 {@link java.lang.Class#isArray()}的区别:</h3>
     * <blockquote>
     * 通常使用<code>instanceof</code>操作符去判断一个对象 <code>object</code> 是否是数组 <code>array</code>.<br>
     * 在JVM层次,<code>instanceof</code>操作符 translates to a specific "instanceof" byte code, which is highly optimized in most JVM
     * implementations.<br>
     * 而反射的方法(getClass().isArray()) is compiled to two separate "invokevirtual" instructions.<br>
     * The more generic optimizations applied by the JVM to these may not be as fast as the hand-tuned optimizations inherent in the
     * "instanceof" instruction.<br>
     * 有两种特殊情况: null references 和 primitive arrays.<br>
     * <table border="1" cellspacing="0" cellpadding="4" summary="">
     * <tr style="background-color:#ccccff">
     * <th align="left"></th>
     * <th align="left"><code>instanceof</code></th>
     * <th align="left"><code>getClass().isArray()</code></th>
     * </tr>
     * <tr valign="top">
     * <td>null reference</td>
     * <td>false</td>
     * <td>NullPointerException</td>
     * </tr>
     * <tr valign="top" style="background-color:#eeeeff">
     * <td>原始类型数组primitive array</td>
     * <td>false</td>
     * <td>true</td>
     * </tr>
     * </table>
     * </blockquote>
     *
     * @param object the object
     * @return 如果 <code>object</code> 是null,抛出 {@link NullPointerException}<br>
     * @see <a href="http://stackoverflow.com/questions/219881/java-array-reflection-isarray-vs-instanceof">Java array reflection: isArray
     * vs. instanceof</a>
     * @see <a href="http://stackoverflow.com/questions/2725533/how-to-see-if-an-object-is-an-array-without-using-reflection">How to see if
     * an object is an array without using reflection?</a>
     */
    public static boolean isArray(Object object) {
        Validate.notNull(object, "object can't be null!");
        return object.getClass().isArray();
    }


    /**
     * 判断指定的对象 <code>object</code> 是否是原生类型数组.
     * 
     * <h3>示例:</h3>
     * 
     * <blockquote>
     * <pre class="code">
     * ObjectUtil.isPrimitiveArray(1)                           = false
     * ObjectUtil.isPrimitiveArray(1L)                          = false
     * ObjectUtil.isPrimitiveArray("1")                         = false
     * ObjectUtil.isPrimitiveArray(new int[] {})                = true
     * ObjectUtil.isPrimitiveArray(new int[] { 1, 2 })          = true
     * ObjectUtil.isPrimitiveArray(new byte[] { 1, 2 })         = true
     * ObjectUtil.isPrimitiveArray(new String[] { "1", "2" })   = false
     * </pre>
     * </blockquote>
     *
     * @param object the object
     * @return 如果 <code>object</code> 是null,抛出 {@link NullPointerException}<br>
     */
    public static boolean isPrimitiveArray(Object object) {
        Validate.notNull(object, "object can't be null!");
        //原始型的
        return isArray(object) && object.getClass().getComponentType().isPrimitive();
    }
}
