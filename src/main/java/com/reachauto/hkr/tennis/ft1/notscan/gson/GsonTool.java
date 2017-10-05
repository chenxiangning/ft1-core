package com.reachauto.hkr.tennis.ft1.notscan.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/21 21:28
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 * <p>
 * <blockquote>
 * <p>
 * <h3>示例:</h3>
 * <p>
 * </blockquote>
 * <p>
 * <blockquote>
 * String jsonx = "{\"kkkkk\":null,\"name\":\"cxn\",\"age\":3,\"code\":1001,\"fen\":88.32,\"xxxxx\":null,\"time\":\"Sep 21, 2017 8:09:53 PM\",\"delFlag\":0,\"createAt\":1505976480000,\"curtime\":1505976606634}";
 * <p>
 * </blockquote>
 * <blockquote>
 * Map map = new HashMap();
 * <p>
 * map.put("name", "cxn");
 * map.put("age", "3");
 * map.put("time", new Date());
 * map.put("xx", null);
 * map.put("code", 1001);
 * map.put("fen", 88.32);
 * <p>
 * Gbean gbean = GsonTool.jsonToBean(GsonTool.getGsonAll(), jsonx, Gbean.class);
 * </blockquote>
 * <p>
 * <p>
 * <blockquote>
 * GsonTool.objectToJsonDateSerializer(gbean, "yyyy-MM-dd : HH:mm:ss");
 * <p>-> 序列化时自定义date {"ooooxxx":null,"kkkkk":null,"name":"cxn","age":3,"code":1001,"fen":88.32,"xxxxx":null,"time":"2017-09-21 : 20:09:53","createAt":"2017-09-21 : 14:48:00","curtime":"2017-09-21 : 14:50:06"}
 * <p>
 * GsonTool.objectToAllFieldEmptyJson(gbean);
 * <p>-> 序列化bean中全部字段时null串替换为"" {"ooooxxx":"","name":"cxn","age":3,"code":1001,"fen":88.32,"xxxxx":"","time":1505995793000,"createAt":1505976480000,"curtime":1505976606634}
 * <p>
 * GsonTool.objectToAllFieldNullJson(gbean);
 * <p>-> 序列化bean中全部字段 {"ooooxxx":null,"kkkkk":null,"name":"cxn","age":3,"code":1001,"fen":88.32,"xxxxx":null,"time":1505995793000,"createAt":1505976480000,"curtime":1505976606634}
 * <p>
 * SGsonTool.objectToNotNullJson(gbean);
 * <p>-> 序列化bean中不等于null的字段 {"name":"cxn","age":3,"code":1001,"fen":88.32,"time":1505995793000,"createAt":1505976480000,"curtime":1505976606634}
 * <p>
 * GsonTool.objectToExposeJson(gbean);
 * <p>->序列化bean中属性添加了@Export的字段 {"code":1001,"fen":88.32,"time":1505995793000,"curtime":1505976606634}
 * <p>
 * <p> @@ {ooooxxx='null'kkkkk='null'name='cxn', age=3, code=1001, fen=88.32, xxxxx='null', time=Thu Sep 21 20:09:53 CST 2017, createAt=Thu Sep 21 14:48:00 CST 2017, curtime=Thu Sep 21 14:50:06 CST 2017}
 * <p> @@ {ooooxxx='null'kkkkk='null'name='cxn', age=3, code=1001, fen=88.32, xxxxx='', time=Thu Sep 21 20:09:53 CST 2017, createAt=Thu Sep 21 14:48:00 CST 2017, curtime=Thu Sep 21 14:50:06 CST 2017}
 * <p> @@ {ooooxxx='null'kkkkk='null'name='cxn', age=3, code=1001, fen=88.32, xxxxx='null', time=Thu Sep 21 20:09:53 CST 2017, createAt=Thu Sep 21 14:48:00 CST 2017, curtime=Thu Sep 21 14:50:06 CST 2017}
 * <p> @@ {ooooxxx='null'kkkkk='null'name='null', age=null, code=1001, fen=88.32, xxxxx='null', time=Thu Sep 21 20:09:53 CST 2017, createAt=null, curtime=Thu Sep 21 14:50:06 CST 2017}
 * <p> @@ {ooooxxx='null'kkkkk='null'name='cxn', age=3, code=1001, fen=88.32, xxxxx='null', time=Thu Sep 21 20:09:53 CST 2017, createAt=Thu Sep 21 14:48:00 CST 2017, curtime=Thu Sep 21 14:50:06 CST 2017}
 * <p>
 * List<Gbean> gbeanList = Arrays.asList(gbean);
 * String listJson = GsonTool.objectToAllFieldEmptyJson(gbeanList);
 * String listJson2 = GsonTool.objectToAllFieldEmptyJson(gbean);
 * <p>
 * ->
 * <p>json:[{"ooooxxx":"","name":"cxn","age":3,"code":1001,"fen":88.32,"xxxxx":"","time":1505995793000,"createAt":1505976480000,"curtime":1505976606634}]
 * <p>json2:{"ooooxxx":"","name":"cxn","age":3,"code":1001,"fen":88.32,"xxxxx":"","time":1505995793000,"createAt":1505976480000,"curtime":1505976606634}
 * <p>json3 [{ooooxxx=, name=cxn, age=3.0, code=1001.0, fen=88.32, xxxxx=, time=1.505995793E12, createAt=1.50597648E12, curtime=1.505976606634E12}]
 * </blockquote>
 */
public final class GsonTool {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonTool.class);

    /**
     * 构建全集,序列化时包含所有字段
     */
    private static Gson gsonAll;
    /**
     * json转换为bean时null字符串会定义为empty
     */
    private static Gson gsonTobeanNullToEmpty;
    /**
     * 当把bean转为json时,bean中的null属性会转为""
     */
    private static Gson beanToGsonNullToEmpty;
    /**
     * 不等于null的属性
     */
    private static Gson gsonNotNull;
    /**
     * bean中属性标记了@Export的会被输出
     */
    private static Gson gsonExpose;

    static {
        if (gsonAll == null) {
            GsonBuilder gb = new GsonBuilder().serializeNulls();
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonAll = gb.create();
        }

        if (gsonTobeanNullToEmpty == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonTobeanNullToEmpty = gb.create();
        }

        if (beanToGsonNullToEmpty == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            beanToGsonNullToEmpty = gb.create();
        }

        if (gsonNotNull == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonNotNull = gb.create();
        }

        if (gsonExpose == null) {
            GsonBuilder gb = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonExpose = gb.create();
        }
    }


    /**
     * Do not instantiate GsonTool.
     */
    private GsonTool() {
        throw new AssertionError("不能实例化,因为它是私有的构造方法! ".concat(getClass().getName()));
    }

    /**
     * Getter for property 'gsonAll'.
     *
     * @return Value for property 'gsonAll'.
     */
    public static Gson getGsonAll() {
        return gsonAll;
    }

    /**
     * Getter for property 'gsonNotNull'.
     *
     * @return Value for property 'gsonNotNull'.
     */
    public static Gson getGsonNotNull() {
        return gsonNotNull;
    }

    /**
     * Getter for property 'gsonExpose'.
     *
     * @return Value for property 'gsonExpose'.
     */
    public static Gson getGsonExpose() {
        return gsonExpose;
    }

    /**
     * Getter for property 'gsonToBeanNullToEmpty'.
     *
     * @return Value for property 'gsonToBeanNullToEmpty'.
     */
    public static Gson getGsonToBeanNullToEmpty() {
        return gsonTobeanNullToEmpty;
    }

    /**
     * Getter for property 'beanToGsonNullToEmpty'.
     *
     * @return Value for property 'beanToGsonNullToEmpty'.
     */
    public static Gson getBeanToGsonNullToEmpty() {
        return beanToGsonNullToEmpty;
    }

    /**
     * 将一个bean转换成Json
     * 范围:base 中标记了 @Expose 的属性
     *
     * @param o
     * @return
     */
    public static String objectToExposeJson(Object o) {
        return gsonExpose.toJson(o);
    }

    /**
     * 将一个bean转换成Json
     * 范围:bean中不等于null的字段
     *
     * @param o
     * @return
     */
    public static String objectToNotNullJson(Object o) {
        return gsonNotNull.toJson(o);
    }

    /**
     * 将一个bean转换成Json
     * 范围:bean的全部属性
     *
     * @param o
     * @return
     */
    public static String objectToAllFieldNullJson(Object o) {
        return gsonAll.toJson(o);
    }

    /**
     * 将一个bean转换成Json
     * 范围:bean的全部属性,null的字符串对象会变为""空串
     *
     * @param o
     * @return
     */
    public static String objectToAllFieldEmptyJson(Object o) {
        return beanToGsonNullToEmpty.toJson(o);
    }

    /**
     * 将对象转换成json格式(并自定义日期格式)
     *
     * @param ts
     * @return
     */
    public static synchronized String objectToJsonDateSerializer(Object ts, final String dateformat) {
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc,
                                         JsonSerializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                return new JsonPrimitive(format.format(src));
            }
        }).serializeNulls().setDateFormat(dateformat).create();
        return gson.toJson(ts);
    }

    /**
     * 将json格式转换成list对象
     *
     * @param gson
     * @param jsonStr
     * @return
     */
    public static List jsonToList(Gson gson, String jsonStr) {
        List<?> objList = null;
        if (gson != null) {
            Type type = new TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * @param gson
     * @param jsonStr
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(Gson gson, String jsonStr, Class<T[]> type) {
        T[] objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return Arrays.asList(objList);
    }

    /**
     * @param gson
     * @param jsonStr
     * @return
     */
    public static Map jsonToMap(Gson gson, String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * @param gson
     * @param jsonStr
     * @param cl
     * @return
     */
    public static <T> T jsonToBean(Gson gson, String jsonStr, Class<?> cl) {
        T obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, (Class<T>) cl);
        }
        return obj;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @param cl
     * @param pattern
     * @param <T>
     * @return
     */
    public static synchronized <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl, final String pattern) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                String dateStr = json.getAsString();
                try {
                    return format.parse(dateStr);
                } catch (ParseException | JsonParseException e) {
                    LOGGER.error("转换为JSON出错{}", e);
                }
                return null;
            }
        }).setDateFormat(pattern).create();
        if (gson != null) {
            return gson.fromJson(jsonStr, cl);
        }
        return null;
    }

    public static Object getJsonValue(Gson gson, String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(gson, jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

}
