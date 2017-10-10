package com.reachauto.hkr.tennis.ft1;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/24 15:25
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
public final class Slf4jTool {
    /**
     * Do not instantiate Slf4jTool.
     */
    private Slf4jTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 格式化字符串,此方法是抽取slf4j的核心方法.
     * 
     * 
     * 在java中,常会拼接字符串生成新的字符串值,在字符串拼接过程中容易写错或者位置写错<br>
     * <br>
     * slf4j的log支持格式化输出log,比如:<br>
     * 
     * 
     * <ul>
     * <li>LOGGER.debug("{}","feilong");</li>
     * <li>LOGGER.info("{},{}","feilong","hello");</li>
     * </ul>
     * 
     * 这些写法非常简洁且有效,不易出错
     * 
     * <br>
     * 因此,你可以在代码中出现这样的写法:
     * 
     * <pre class="code">
     * throw new IllegalArgumentException(Slf4jUtil.format(
     * "callbackUrl:[{}] ,length:[{}] can't {@code >}{}",
     * callbackUrl,
     * callbackUrlLength,
     * callbackUrlMaxLength)
     * </pre>
     * 
     * 又或者
     * 
     * <pre class="code">
     * return Slf4jUtil.format("{} [{}]", encode, encode.length());
     * </pre>
     *
     * @param messagePattern message的格式,比如 callbackUrl:[{}] ,length:[{}]
     * @param args           参数
     * @return 如果 <code>messagePattern</code> 是null,返回 null<br>
     * 如果 <code>args</code> 是null,返回 <code>messagePattern</code><br>
     * @see org.slf4j.helpers.FormattingTuple
     * @see org.slf4j.helpers.MessageFormatter#arrayFormat(String, Object[])
     * @see org.slf4j.helpers.FormattingTuple#getMessage()
     */
    public static String format(String messagePattern, Object... args) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messagePattern, args);
        return formattingTuple.getMessage();
    }
}
