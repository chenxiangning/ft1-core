package com.reachauto.hkr.tennis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UUID生成类
 */
public final class UUIDGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDGenerator.class);
    public static final int TEN = 10;

    /**
     * 数字型UUID设置 数字型UUID = 毫秒数 + 随机数，且固定长度 其中 毫秒数= 当前毫秒数 - 日期基数毫秒数
     */
    // 日期基数毫秒数
    private static Long baseDateForNumber;
    // number UUID的位数，生成的UUID最大为此位数
    private static Integer digitsForNumber;
    // 默认的日期基数
    private static String defaultBaseDateForNumber = "2014-07-03";
    // number UUID的最小位数，设置的digitsForNumber不能超过此值
    private static final int MIN_DIGITS_FOR_NUMBER = 13;
    // number UUID的最大位数，设置的digitsForNumber不能低于此值
    private static final int MAX_DIGITS_FOR_NUMBER = 19;

    /**
     * 日期型 <br/>
     * 日期型UUID的最大位数，设置的digitsForNumber不能低于此值
     */
    private static final int DIGITS_FOR_DATE = 19;

    private UUIDGenerator() {
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuidStr = UUID.randomUUID().toString();
        // 去掉“-”符号
        return uuidStr.replace("-", "");
    }

    /**
     * 设置数字型UUID的基础信息，只能设置一次
     * Date 2014年7月3日 下午3:22:06
     *
     * @param digits 数字型ID的长度
     * @author jiangbo
     */
    public synchronized static void setBaseInfoForNumber(Integer digits) {
        if (baseDateForNumber != null) {
            return;
        }

        /**
         * 数字型的基础毫秒数
         */
        try {
            baseDateForNumber = new SimpleDateFormat("yyyy-MM-dd").parse(defaultBaseDateForNumber)
                    .getTime();
        } catch (ParseException e) {
            LOGGER.error("setBaseDateForNumber error", e);
        }

        /**
         * 随机数相关处理
         */
        if (digits != null) {
            if (digits < MIN_DIGITS_FOR_NUMBER) {
                digitsForNumber = MIN_DIGITS_FOR_NUMBER;
            } else if (digits > MAX_DIGITS_FOR_NUMBER) {
                digitsForNumber = MAX_DIGITS_FOR_NUMBER;
            } else {
                digitsForNumber = digits;
            }
        }

    }

    /**
     * 得到数字型的UUID
     * Date 2014年7月3日 下午1:33:34
     *
     * @return UUID
     */
    public static Long getNumberUUID() {
        /**
         * 初始化基础日期配置
         */
        if (baseDateForNumber == null) {
            setBaseInfoForNumber(null);
        }

        /**
         * 获取当前与基础日期的毫秒数差值
         */
        long now = new Date().getTime();
        Long uuid = now - baseDateForNumber;

        StringBuilder sf = new StringBuilder(digitsForNumber);
        sf.append(uuid.toString());

        /**
         * 生成随机数补位
         */
        // 随机数补位位数 = ID固定长度 -
        int len = digitsForNumber - MIN_DIGITS_FOR_NUMBER;
        // 13（不可能出现14位，除非用到2330年以后）
        if (len > 0) {
            sf.append(getRandom(len));
            uuid = Long.parseLong(sf.toString());
        }

        return uuid;

    }

    /**
     * 得到一组id（排除重复）
     * Date 2014年10月30日 下午3:42:42
     *
     * @param size size
     * @return UUID
     */
    public static Long[] getDateUUID(int size) {

        List<Long> ids = new ArrayList<>(size);
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Long id = getDateUUID();
            if (map.get(id) != null) {
                continue;
            }
            map.put(id, id);
            ids.add(id);
        }

        return ids.toArray(new Long[0]);
    }

    /**
     * 得到日期格式数字型UUID yyMMddhhmmssSS + 4位随机数,共19位
     * @return uuid uuid
     */
    public static Long getDateUUID() {
        Date date = new Date();
        return getDateUUID(date);
    }

    /**
     * 得到日期格式数字型UUID yyMMddhhmmssSS + 4位随机数,共19位
     * @return uuid
     */
    public static BigInteger getBigIntUUID() {
        String longId = getUUID();
        return new BigInteger(longId);
    }

    /**
     * 根据传入的业务时间得到日期格式数字型UUID
     * Date 2014年10月30日 下午3:41:42
     *
     * @param date date
     * @return UUID
     */
    public static Long getDateUUID(Date date) {
        String now = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        StringBuilder sf = new StringBuilder(DIGITS_FOR_DATE);
        sf.append(now);
        int len = DIGITS_FOR_DATE - now.length();
        sf.append(getRandom(len));
        long uuid = Long.parseLong(sf.toString());
        return uuid;
    }

    /**
     * 生成随机数，前面补零
     * Date 2014年7月3日 下午4:27:17
     *
     * @param digits digits
     * @return 随机数
     */
    private static StringBuilder getRandom(int digits) {
        StringBuilder sf = new StringBuilder(digits);

        int maxRandomForNumber = (int) Math.pow(TEN, digits);
        // 生成随机数
        Integer random = new SecureRandom().nextInt(maxRandomForNumber);
        // 补零
        for (int i = 0; i < digits - random.toString().length(); i++) {
            sf.append("0");
        }
        sf.append(random.toString());
        return sf;
    }

}
