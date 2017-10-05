package com.reachauto.hkr.tennis.ft1;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-09-28 14:02
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 正则匹配器工具
 * <p>
 * <p>
 * <style type="text/css">
 * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
 * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
 * .tftable tr {background-color:#ffffff;}
 * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
 * </style>
 * <p>
 * <table class="tftable" border="1">
 * <tr><th>--</th><th>--</th></tr>
 * <tr><td>Pattern.CASE_INSENSITIVE</td><td> 不区分大小写</td></tr>
 * <tr><td>Pattern.COMMENTS</td><td> 在这种模式下，匹配时会忽略(正则表达式里的)空格字符(注：不是指表达式里的"//s"，而是指表达式里的空格，tab，回车之类)。
 * <br/>注释从#开始，一直到这行结束。可以通过嵌入式的标志来启用Unix行模式。</td></tr>
 * <tr><td>Pattern.DOTALL</td><td>在这种模式下，表达式'.'可以匹配任意字符，包括表示一行的结束符。默认情况下，表达式'.'不匹配行的结束符。</td></tr>
 * <tr><td>Pattern.MULTILINE</td><td>多行模式,在这种模式下，'^'和'$'分别匹配一行的开始和结束。此外，'^'仍然匹配字符串的开始，'$'也匹配字符串的结束。
 * <br/>默认情况下，这两个表达式仅仅匹配字符串的开始和结束。</td></tr>
 * <tr><td>Pattern.UNIX_LINES</td><td> 	在这个模式下，只有'/n'才被认作一行的中止，并且与'.'，'^'，以及'$'进行匹配。</td></tr>
 * <tr><td> </td><td> </td></tr>
 * </table>
 * <p>
 */
public final class RegExTool {

    private RegExTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 原数据匹配正则,如果可以匹配上则返回true,否则返回false.
     * <blockquote>
     * <style type="text/css">
     * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
     * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
     * .tftable tr {background-color:#ffffff;}
     * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
     * </style>
     * <p>
     * <table id="tfhover" class="tftable" border="1">
     * <tr><th>示例</th></tr>
     * <tr><td>这个段代码运行的话，将会在控制台输出“true”。它是说字符串 str 与正则表达式 regex 匹配，换句话说，<br/>
     * 就是字符串 str 符合正则表达式 regex 所描述的模式。在该例子中，最起码我们可以知道正则表达式的操作对象是字符串，<br/>
     * 而正则表达式也是一个字符串。字符串又是由字符所构成的，在表达式 go*gle 中 g,o,l,e 是文字字符而 * 是数量限定字符，<br/>
     * 它代表前面的字符可以不出现，也可以出现一次或者多次。</td></tr>
     * <tr><td>Assert.assertEquals(RegExTool.matches("iwgoooooooooooood","i.+d"), true)</td></tr>
     * <tr><td>Assert.assertEquals(RegExTool.matches("iwgoooooooooooood","iwgo+d"), true)</td></tr>
     * <tr><td>Assert.assertEquals(RegExTool.matches("iwgoooooooooooood","iwgo"), false)</td></tr>
     * </table>
     * </blockquote>
     *
     * @param source 要匹配的字符串
     * @param regex  正则表达式
     * @return
     */
    public static boolean matches(String source, String regex) {
        Validate.notNull(source, "source can't be null");
        Validate.notBlank(source, "sourceStr can't be blank!");
        Validate.notNull(regex, "regex can't be null");
        Validate.notBlank(regex, "regex can't be blank!");

        return source.matches(regex);
    }


    /**
     * 原数据匹配正则,如果可以匹配上则返回true,否则返回false.
     * <blockquote>
     * <style type="text/css">
     * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
     * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
     * .tftable tr {background-color:#ffffff;}
     * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
     * </style>
     * <p>
     * <table id="tfhover" class="tftable" border="1">
     * <tr><th>示例</th></tr>
     * <tr><td>RegExTool.groupCaseInsensitive("About Cats and dogs----cat", "cat")</td></tr>
     * </table>
     * </blockquote>
     *
     * @param source 要匹配的字符串
     * @param regex  正则表达式
     *
     * @return
     */
    public static List<String> groupCaseInsensitive(String source, String regex) {
        return group(source, regex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * 正则匹配,开启反向引用,返回引用中的值
     * <blockquote>
     * <style type="text/css">
     * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
     * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
     * .tftable tr {background-color:#ffffff;}
     * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
     * </style>
     * <p>
     * <table id="tfhover" class="tftable" border="1">
     * <tr><th>示例</th></tr>
     * <tr><td>
     * String regex = "(\\w+)@(\\w+\\.\\w+)"<br/>
     * String souse = "chenxiangning@126.com"<br/>
     * RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,0);<br/>
     * ---> chenxiangning@126.com<br/>
     * RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,1);<br/>
     * ---> chenxiangning<br/>
     * RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,2);<br/>
     * ---> 126.com
     * </td></tr>
     * </table>
     * </blockquote>
     *
     * @param source     要匹配的字符串
     * @param regex      正则表达式
     * @param flags      匹配模式
     * @param groupIndex 反向引用值下标
     * @return
     */
    public static String groupAndMatches(String source, String regex, int flags, int groupIndex) {
        Validate.notNull(source, "source can't be null");
        Validate.notBlank(source, "source can't be blank!");
        Validate.notNull(regex, "regex can't be null");
        Validate.notBlank(regex, "source can't be blank!");

        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(source);
        matcher.matches();
        return matcher.group(groupIndex);
    }

    /**
     * 正则匹配,开启反向引用,返回引用中的值
     * <blockquote>
     * <style type="text/css">
     * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
     * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
     * .tftable tr {background-color:#ffffff;}
     * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
     * </style>
     * <p>
     * <table id="tfhover" class="tftable" border="1">
     * <tr><th>示例</th></tr>
     * <tr><td>
     * String regex = "(\\w+)@(\\w+\\.\\w+)"<br/>
     * String souse = "chenxiangning@126.com"<br/>
     * RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,0);<br/>
     * ---> [chenxiangning@126.com, chenxiangning, 126.com]<br/>
     * </td></tr>
     * </table>
     * </blockquote>
     *
     * @param source     要匹配的字符串
     * @param regex      正则表达式
     * @param flags      匹配模式
     * @return
     */
    public static List<String> groupAndMatches(String source, String regex, int flags) {
        Validate.notNull(source, "source can't be null");
        Validate.notBlank(source, "source can't be blank!");
        Validate.notNull(regex, "regex can't be null");
        Validate.notBlank(regex, "source can't be blank!");

        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(source);
        matcher.matches();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < matcher.groupCount() + 1; i++) {
            results.add(matcher.group(i));
        }

        return results;
    }

    /**
     * 原数据匹配正则,如果可以匹配上则返回true,否则返回false.
     * <blockquote>
     * <style type="text/css">
     * .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
     * .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
     * .tftable tr {background-color:#ffffff;}
     * .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
     * </style>
     * <p>
     * <table id="tfhover" class="tftable" border="1">
     * <tr><th>示例</th></tr>
     * <tr><td>RegExTool.group("About Cats and dogs----cat", "cat",Pattern.CASE_INSENSITIVE)</td></tr>
     * </table>
     * </blockquote>
     *
     * @param source 要匹配的字符串
     * @param regex  正则表达式
     * @param flags  匹配模式
     * @return
     */
    public static List<String> group(String source, String regex, int flags) {
        Validate.notNull(source, "source can't be null");
        Validate.notBlank(source, "source can't be blank!");
        Validate.notNull(regex, "regex can't be null");
        Validate.notBlank(regex, "source can't be blank!");

        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(source);
        List<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }
}