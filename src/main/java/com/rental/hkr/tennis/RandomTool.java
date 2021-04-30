package com.rental.hkr.tennis;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 14:19
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 * 随机数工具类.
 *
 * <ul>
 * <li>{@link java.lang.Math#random()}底层也是调用的 new Random(),值＝Random nextDouble()</li>
 * <li>把 {@link java.util.Random}对象作为一个全局实例(static)来使用.Java中 {@link java.util.Random} 是线程安全的(内部进行了加锁处理);</li>
 * <li>伪随机数</li>
 * <li>生成随机数的算法有很多种,最简单也是最常用的就是 "线性同余法":第n+1个数=(第n个数*29+37) % 1000,其中%是"求余数"运算符.</li>
 * </ul>
 *
 * @see java.lang.Math#random()
 * @see org.apache.commons.lang3.RandomUtils
 * @see org.apache.commons.lang3.RandomStringUtils
 */
public class RandomTool {

    /**
     * Random object used by random method.
     *
     *
     * 为了确保同一毫秒不能返回相同的值,不同声明在方法里面.<br>
     * 把Random对象作为一个全局实例(static)来使用. Java中Random是线程安全的(内部进行了加锁处理);
     *
     *
     * @see org.apache.commons.lang3.RandomUtils
     * @since 1.0.7
     */
    private static final Random JVM_RANDOM = new Random();

    /**
     * Do not instantiate RandomTool.
     */
    private RandomTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }


    /**
     * 生成一个指定长度<code>length</code>的 <b>随机正整数</b>.
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * RandomUtil.createRandomWithLength(2)
     * 生成的结果是可能是 89
     *
     * </blockquote>
     *
     * @param length 设定所取出随机数的长度.
     * @return 如果 <code>length</code> {@code <=0} ,抛出 {@link IllegalArgumentException}
     */
    public static long createRandomWithLength(int length) {
        Validate.isTrue(length > 0, "input param [length] must >0,but is [%s]", length);
        long num = 1;
        for (int i = 0; i < length; ++i) {
            num = num * 10;
        }

        // 该值大于等于 0.0 且小于 1.0 正号的 double 值
        double random = JVM_RANDOM.nextDouble();
        // 可能出现 0.09346924349151808
        random = random < 0.1 ? random + 0.1 : random;
        return (long) (random * num);
    }


    /**
     * 随机抽取字符串<code>char</code>,拼接成指定长度<code>length</code>的字符串.
     *
     * <h3>说明:</h3>
     * <blockquote>
     * <ol>
     * <li>常用于生成验证码</li>
     * </ol>
     * </blockquote>
     *
     * <h3>示例:</h3>
     * <blockquote>
     *
     * </blockquote>
     *
     * @param str    被抽取的字符串
     * @param length 指定字符串长度,比如 5
     * @return 如果 <code>str</code> 是null,抛出 {@link NullPointerException}<br>
     * 如果 <code>str</code> 是blank,抛出 {@link IllegalArgumentException}<br>
     * 如果 <code>length</code> {@code <=0}, 抛出 {@link IllegalArgumentException}
     * @see org.apache.commons.lang3.RandomStringUtils#random(int, String)
     */
    public static String createRandomFromString(String str, int length) {
        Validate.notBlank(str, "str can't be null/empty!");
        Validate.isTrue(length > 0, "input param [length] must >0,but is [%s]", length);
        return RandomStringUtils.random(length, str);
    }
}
