package com.reachauto.hkr.tennis.ft1.notscan.base;

import com.google.common.base.Joiner;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/29 21:25
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 * <p>
 * <p>
 * <style type="text/css">
 * table.tftable {font-size:12px;color:#fbfbfb;width:100%;border-width: 1px;border-color: #686767;border-collapse: collapse;}
 * table.tftable th {font-size:12px;background-color:#171515;border-width: 1px;padding: 8px;border-style: solid;border-color: #686767;text-align:left;}
 * table.tftable tr {background-color:#2f2f2f;}
 * table.tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #686767;}
 * </style>
 * <p>
 * <table id="tfhover" class="tftable" border="1">
 * <tr><th>字符串处理,分割,连接,填充</th></tr>
 * <tr><td>Join String</td></tr>
 * <tr><td>Assert.assertEquals("1,5,7", JoinTool.on(",").join(Arrays.asList(1, 5, 7)))</td></tr>
 * <tr><td>Assert.assertEquals("a,b,c", JoinTool.on(",").join(new String[]{"a", "b", "c"}))</td></tr>
 * <tr><td>Assert.on(",").join(new String[]{"a", null, "c"}) 这个回报空指针</td></tr>
 * <tr><td>Assert.assertEquals("dd#ss", JoinTool.on("#").skipNulls().join(new String[]{null, null, "dd", "ss"}))</td></tr>
 * <tr><td>Assert.assertEquals("替换#替换#dd#ss", JoinTool.on("#").useForNull("替换").join(new String[]{null, null, "dd", "ss"}))</td></tr>
 * <tr><td>Assert.assertEquals("头ddss", JoinTool.on("").appendTo(new StringBuilder("头"), new String[]{"dd", "ss"}).toString())</td></tr>
 * <tr><td>
 * Map<Integer, String> map = new HashMap<Integer, String>();<br/>
 * map.put(1, "a");<br/>
 * map.put(2, "b");<br/>
 * Assert.assertEquals("1->a|2->b", JoinTool.on("|").withKeyValueSeparator("->").join(map));
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
