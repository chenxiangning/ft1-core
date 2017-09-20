package com.tennis.ft1.notscan.gson;

import com.google.gson.*;
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
 * Created by chenxiangning on 2015/8/9.
 */
public final class GsonTool {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonTool.class);

    private static Gson gsonAll;
    private static Gson gsonNotNull;
    private static Gson gsonExpose;

    static {
        if (gsonAll == null) {
            GsonBuilder gb = new GsonBuilder().serializeNulls();
            gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonAll = gb.create();
        }

        if (gsonNotNull == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonNotNull = gb.create();
        }

        if (gsonExpose == null) {
            GsonBuilder gb = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gsonExpose = gb.create();
        }
    }

    public static Gson getGsonAll() {
        return gsonAll;
    }

    public static Gson getGsonNotNull() {
        return gsonNotNull;
    }

    public static Gson getGsonExpose() {
        return gsonExpose;
    }

    private GsonTool() {
    }

    public static String objectToExposeJson(Object o) {
        return gsonExpose.toJson(o);
    }

    public static String objectToNotNullJson(Object o) {
        return gsonNotNull.toJson(o);
    }

    public static String objectToAllFieldJson(Object o) {
        return gsonAll.toJson(o);

    }
    public static String objectToAllFieldJson222(Object o) {
        GsonBuilder gson = new GsonBuilder()
                .serializeNulls();
//        gson.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
//        gson.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);

        return gson.create().toJson(o);
    }


    /**
     * 将对象转换成json格式(并自定义日期格式)
     *
     * @param ts
     * @return
     */
    public static synchronized String objectToJsonDateSerializer(Object ts, final String dateformat) {
        gsonAll = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc,
                                         JsonSerializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                return new JsonPrimitive(format.format(src));
            }
        }).setDateFormat(dateformat).create();
        return gsonAll.toJson(ts);
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
            Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
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
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
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
        gsonAll = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
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
        if (gsonAll != null) {
            return gsonAll.fromJson(jsonStr, cl);
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
