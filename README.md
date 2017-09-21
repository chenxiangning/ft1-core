# ft1-core

### 工具包分包想法

`notscan` 包代表不需要spring容器进行管理就可以直接使用的包.

该包中的工具类多为Tool结尾,静态的工具类使用方式多为Tool.method

---

`springscan` 包代表需要spring容器进行管理.

我们一般使用spring boot开发,在引入该包之后需要扫描该包下的类才可以使用.

### Gson
2017年9月21日 23:18:13

添加了 json 的序列化和反序列化的工具,使用的事Gson它非常的方便

### Redis 
2017年9月21日 23:18:19

添加了Redis的操作可以很简单的对redis操作


