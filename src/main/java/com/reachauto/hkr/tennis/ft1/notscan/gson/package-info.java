package com.reachauto.hkr.tennis.ft1.notscan.gson;


/**
 * Gson处理对象的几个重要点
 * <p>
 * 1 推荐把成员变量都声明称private的
 * <p>
 * 2 没有必要用注解（@Expose 注解）指明某个字段是否会被序列化或者反序列化，所有包含在当前类（包括父类）中的字段都应该默认被序列化或者反序列化
 * <p>
 * 3 如果某个字段被 transient 这个Java关键词修饰，就不会被序列化或者反序列化
 * <p>
 * 4 下面的实现方式能够正确的处理null
 * 1）当序列化的时候，如果对象的某个字段为null，是不会输出到Json字符串中的。
 * 2）当反序列化的时候，某个字段在Json字符串中找不到对应的值，就会被赋值为null
 * <p>
 * 5 如果一个字段是 synthetic
 * 的,他会被忽视，也即是不应该被序列化或者反序列化
 * <p>
 * 6 内部类（或者anonymous class（匿名类），或者local class(局部类，可以理解为在方法内部声明的类)）的某个字段和外部类的某个字段一样的话，就会被忽视，不会被序列化或者反序列化
 * <p>
 *
 * @SerializedName("字段别名")
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 * @Since和@Until注解 Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 * <p>
 * <p>
 * public class SoccerPlayer { private String name; @Since(1.2) private int shirtNumber; @Until(0.9) private String country; private String teamName; // Methods removed for brevity }
 * @SerializedName("字段别名")
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 * @Since和@Until注解 Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 * @Since(1.2) private int shirtNumber;
 * @SerializedName("字段别名")
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 * @Since和@Until注解 Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 * @Since(1.2) private int shirtNumber;
 * @SerializedName("字段别名")
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 * @Since和@Until注解 Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 * @Since(1.2) private int shirtNumber;
 * @Until(0.5) private int shirtNumber;
 * 也就是说我们利用方法builder.setVersion(1.0)定义版本1.0，如下
 * @SerializedName("字段别名")
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 * @Since和@Until注解 Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 * @Since(1.2) private int shirtNumber;
 * @Until(0.5) private int shirtNumber;
 * 也就是说我们利用方法builder.setVersion(1.0)定义版本1.0，如下
 */
/**
 * @SerializedName("字段别名")
 *
 * @Expose 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）
 * @Expose(deserialize = true)
 * @Expose(deserialize = false)
 * 需要注意的通过 builder.excludeFieldsWithoutExposeAnnotation()方法是该注解生效
 *
 *
 * @Since和@Until注解
 *
 * Since代表“自从”，Until 代表”一直到”。它们都是针对该字段生效的版本。
 * 比如说 @Since(1.2)代表从版本1.2之后才生效，@Until(0.9)代表着在0.9版本之前都是生效的。
 *
 * @Since(1.2) private int shirtNumber;
 * @Until(0.5) private int shirtNumber;
 * 也就是说我们利用方法builder.setVersion(1.0)定义版本1.0，如下
 */