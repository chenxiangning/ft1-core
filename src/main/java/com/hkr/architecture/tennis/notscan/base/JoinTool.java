package com.hkr.architecture.tennis.notscan.base;

import com.google.common.base.Joiner;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/29 21:25
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 *
 * <table border="1" cellspacing="0" cellpadding="4" summary="">
 * <tr style="background-color:#ccccff">
 * <th align="left">Join String</th>
 * </tr>
 * <tr><td>Assert.assertEquals("1,5,7", JoinTool.on(",").join(Arrays.asList(1, 5, 7)))</td></tr>
 * <tr><td>Assert.assertEquals("a,b,c", JoinTool.on(",").join(new String[]{"a", "b", "c"}))</td></tr>
 * <tr><td>Assert.on(",").join(new String[]{"a", null, "c"}) 这个回报空指针</td></tr>
 * <tr><td>Assert.assertEquals("dd#ss", JoinTool.on("#").skipNulls().join(new String[]{null, null, "dd", "ss"}))</td></tr>
 * <tr><td>Assert.assertEquals("替换#替换#dd#ss", JoinTool.on("#").useForNull("替换").join(new String[]{null, null, "dd", "ss"}))</td></tr>
 * <tr><td>Assert.assertEquals("头ddss", JoinTool.on("").appendTo(new StringBuilder("头"), new String[]{"dd", "ss"}).toString())</td></tr>
 * <tr><td>
 * Map map = new HashMap();<br>
 * map.put(1, "a");<br>
 * map.put(2, "b");<br>
 * Assert.assertEquals("1-a$2-b", JoinTool.on("$").withKeyValueSeparator("-").join(map));
 * </td></tr>
 * </table>
 */
public final class JoinTool {
    private JoinTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    public static Joiner on(String separator) {
        return Joiner.on(separator);
    }

    public static Joiner on(char separator) {
        return Joiner.on(separator);
    }

}
