package com.rental.hkr.tennis;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExToolTest {


    @Test
    public void matchesTest() {
        Assert.assertEquals(RegExTool.matches("iwgoooooooooooood", "i.+d"), true);
        Assert.assertEquals(RegExTool.matches("iwgoooooooooooood", "iwgo+d"), true);
        Assert.assertEquals(RegExTool.matches("iwgoooooooooooood", "iwgo"), false);
    }

    /**
     * 最基本的正则表达式由单个文字符号组成。如 a ，它将匹配字符串中第一次出现的字符“a”。如对字符串“Jack is a boy”。
     * “J”后的“a”将被匹配。而第二个“a”将不会被匹配。正则表达式也可以匹配第二个“a”，
     * 这必须是你告诉正则表达式引擎从第一次匹配的地方开始搜索。在文本编辑器中，你可以使用“查找下一个”。
     * 如果变成 Java 代码的话就是这样：
     */
    @Test
    public void regexTest2() {
        String str = "Jack is a boy";
        //将字符串编译为正则表达式的对象表示形式
        Pattern pattern = Pattern.compile("\\w");
        //创建对字符串str根据正则表达式pattern进行匹配才做的匹配器对象
        Matcher matcher = pattern.matcher(str);
        //查找下一个匹配的字符串内容们如果找到 返回true 找不到返回false
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 类似的，cat 会匹配“About cats and dogs”中的“cat”。这等于是告诉正则表达式引擎，
     * 找到一个c，紧跟一个a，再跟一个t。要注意，正则表达式引擎缺省是大小写敏感的。除非你告诉引擎忽略大小写，
     * 否则 cat 不会匹配“Cat”，就像下面这样。
     * （除了这种方法，还可以在表达式内声明什么内容需要区分大小写什么不需要，这在后面会有介绍）
     *
     */
    @Test
    public void regexTest3(){
        System.out.println(RegExTool.groupCaseInsensitive("About Cats and dogs----cat", "cat"));

    }

    @Test
    public void regexTest8() {
        String regex = "(\\w+)@(\\w+\\.\\w+)";
        String souse = "chenxiangning@126.com";

        System.out.println(RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE));
        System.out.println(RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,0));
        System.out.println(RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,1));
        System.out.println(RegExTool.groupAndMatches(souse, regex, Pattern.MULTILINE,2));

        ImmutableList list = ImmutableList.builder().add(1).add(2).build();

        list.forEach(o -> System.out.println(o));
    }

}
