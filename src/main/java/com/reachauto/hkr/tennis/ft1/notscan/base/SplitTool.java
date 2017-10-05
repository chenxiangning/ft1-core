package com.reachauto.hkr.tennis.ft1.notscan.base;

import com.google.common.base.Splitter;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/29 20:38
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 * 这是一个字符串拆分的工具类,示例
 * <p>
 * <style type="text/css">
 * table.tftable {font-size:12px;color:#fbfbfb;width:100%;border-width: 1px;border-color: #686767;border-collapse: collapse;}
 * table.tftable th {font-size:12px;background-color:#171515;border-width: 1px;padding: 8px;border-style: solid;border-color: #686767;text-align:left;}
 * table.tftable tr {background-color:#2f2f2f;}
 * table.tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #686767;}
 * </style>
 * <p>
 * <table id="tfhover" class="tftable" border="1">
 * <tr><th>示例</th></tr>
 * <tr><td>Split String</td></tr>
 * <tr><td>SplitTool.on(' ').split("1 2 3") <br/>[1, 2, 3]</td></tr>
 * <tr><td>SplitTool.onPattern("\\s+").split("1    2 3")    <br/>["1", "2", "3"]</td></tr>
 * <tr><td>SplitTool.fixedLength(2).split("123456789ABCD"))   <br/>[12, 34, 56, 78, 9A, BC, D]</td></tr>
 * <tr><td>Split Map</td></tr>
 * <tr><td>SplitTool.on("#").withKeyValueSeparator(":").split("1:2#3:4")  <br/>{1=2, 3=4} </td></tr>
 * <tr><td>SplitTool.on("#").withKeyValueSeparator(":").split("1:2#3:4")  <br/> java.lang.IllegalArgumentException: Chunk [3:4:5] is not a valid entry</td></tr>
 * </table>
 */
public final class SplitTool {

    private SplitTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    public static Splitter on(String separator) {
        return Splitter.on(separator);
    }

    public static Splitter on(char separator) {
        return Splitter.on(separator);
    }

    public static Splitter onPattern(String separatorPattern) {
        return Splitter.onPattern(separatorPattern);
    }

    public static Splitter fixedLength(final int length) {
        return Splitter.fixedLength(length);
    }


}
