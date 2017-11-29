package com.reachauto.hkr.tennis.ft1;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-27 15:08
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 字符串的操作
 */
public class StringTool {
    /**
     * Do not instantiate ObjectTool.
     */
    private StringTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * 大写首字母<br>
     * 例如：str = name, return Name
     *
     * @param str 字符串
     * @return 字符串
     */
    public static String upperFirst(CharSequence str) {
        if (ValidatorTool.isNullOrEmpty(str)) {
            return null;
        }

        if (str.length() > 0 && str.length() == 1) {
            char firstChar = str.charAt(0);
            if (Character.isLowerCase(firstChar)) {
                return String.valueOf(Character.toUpperCase(firstChar));
            }
        } else if (str.length() > 1) {
            char firstChar = str.charAt(0);
            if (Character.isLowerCase(firstChar)) {
                return String.valueOf(Character.toUpperCase(firstChar)).concat(String.valueOf(str).substring(1));
            }
        }

        return str.toString();
    }

    /**
     * 小写首字母<br>
     * 例如：str = Name, return name
     *
     * @param str 字符串
     * @return 字符串
     */
    public static String lowerFirst(CharSequence str) {
        if (ValidatorTool.isNullOrEmpty(str)) {
            return null;
        }
        if (str.length() > 0 && str.length() == 1) {
            char firstChar = str.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return String.valueOf(Character.toLowerCase(firstChar));
            }
        } else if (str.length() > 1) {
            char firstChar = str.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return String.valueOf(Character.toLowerCase(firstChar)).concat(String.valueOf(str).substring(1));
            }
        }
        return str.toString();
    }

    /**
     * 去掉首部指定长度的字符串并将剩余字符串首字母小写<br>
     * 例如：str=setName, preLength=3 =》 return name
     *
     * @param str       被处理的字符串
     * @param preLength 去掉的长度
     * @return 处理后的字符串，不符合规范返回null
     */
    public static String removePreAndLowerFirst(CharSequence str, int preLength) {
        if (str == null) {
            return null;
        }
        if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            if (str.length() > preLength + 1) {
                return first + str.toString().substring(preLength + 1);
            }
            return String.valueOf(first);
        } else {
            return str.toString();
        }
    }


    /**
     * 原字符串首字母大写并在其首部添加指定字符串 例如：str=name, preString=get =》 return getName
     *
     * @param pre 前缀
     * @param str 被处理的字符串
     * @return 处理后的字符串
     */
    public static String upperFirstAndAddPre(String pre, CharSequence str) {
        if (ValidatorTool.isNullOrEmpty(pre) || ValidatorTool.isNullOrEmpty(str)) {
            return null;
        }
        return pre + upperFirst(str);
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }
        return str.toString().getBytes(charset);
    }

    /**
     * 解码字节码
     *
     * @param data 字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * 字符串是否为空，空的定义如下 1、为null <br>
     * 2、为""<br>
     *
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }


    /**
     * 比较两个字符串是否相等。
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @param ignoreCase 是否忽略大小写
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     * @since 3.2.0
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (str1 == null) {
            return str2 == null;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.equals(str2);
        }
    }

}