# ft1-core

### 工具包分包想法

本包中的工具代码不包含业务代码.纯粹是一下常用工具的封装.
不涉及泄密等信息.

`notscan` 包代表不需要spring容器进行管理就可以直接使用的包.

该包中的工具类多为Tool结尾,静态的工具类使用方式多为Tool.method



### 意图

    使我们从大量重复的底层代码中脱身提高工作效率
    让你的代码更简炼，易写、易读、易于维护.


---

`springscan` 包代表需要spring容器进行管理.

我们一般使用spring boot开发,在引入该包之后需要扫描该包下的类才可以使用.

### Gson
2017年9月21日 23:18:13

添加了 json 的序列化和反序列化的工具,使用的事Gson它非常的方便

### Redis 
2017年9月21日 23:18:19

添加了Redis的操作可以很简单的对redis操作

### Date
2017年9月24日 16:48:27

添加了对日期的操作,一些常规的计算很有精简代码的作用.

### Random
2017年9月24日 16:49:15

随机数生成提供指定位数的随机数生成,也支持指定字符串的随机摘取.

### Validator
2017年9月24日 16:50:23

简单的校验
    ObjectTool
    ValidatorTool
    
    
### 常用的常量
2017年9月24日 16:52:03

DatePattern,CharsetType

### cache 注解使用
2017年9月27日 23:35:15

添加了2个注解,这两个注解完全解放模版代码.

`@HkrCache` 和 `@HkrCacheDel`

@HkrCache 的参数列表
* String model 模块名称 例如 mn, fe, he 等
* int expire 缓存失效的时间,默认是60秒钟.
      
想使用@HkrCache缓存方法的返回值,需要注意的事项
* 参数中的自定义Class必须要实现toString方法.
* 缓存的存储模式 key-val 不支持 key-hash

@HkrCache 作用范围读写,缓存方法的返回结果到Redis,缓存的key是用的类明.方法(参数列表).参数值

    例如 : hkr:cache:mn:DemoServiceImpl.demoFindById(String):sdsd
          hkr:cache:mn:DemoServiceImpl.getBean(int,Integer,String,DemoBean):1,2,3,DemoBean{name='demo'}
     

@HkrCacheDel 缓存清除的主要注解

参数列表
* String model 模块名称 例如 mn, fe, he 等
* String[] delKey 要删除的key,它的格式参照生成缓存的key去设置.最后给个*号

注意事项
* 缓存暂时不支持Hash类型的删除

### add BigDecimalTool
2017年9月28日 00:50:10

### add RegExTool 

### add google guava 

封装 JoinTool SplitTool

### redisconfig 修改名称
### gson 添加default的转换方式.
### guava添加了几个demo

### add ali mq 封装

### 解决cache中反射方法返回体为list泛型的解析bug

### 6.x
HexTool 16进制转换类
StringTool 字符串判断等操作类
PointAreaTool 点区域判断
add 时间计算


add cache del all


包结构中ft1删除


mvn clean deploy -P sonatype-oss-release
到时候需要输入 my8640996










