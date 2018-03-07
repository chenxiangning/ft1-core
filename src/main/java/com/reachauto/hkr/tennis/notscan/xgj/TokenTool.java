package com.reachauto.hkr.tennis.notscan.xgj;

import org.apache.commons.lang3.Validate;

/**
 * token 共通类
 * Date 2015年8月11日 下午2:27:06
 *
 * @author fanjm
 */
public final class TokenTool {

    /**
     * 构造方法
     * Date 2015年8月11日 下午2:37:43f
     *
     * @author fanjm
     */
    private TokenTool() {
    }

    /**
     * 16进制 数组
     */
    public static final String HEX_DIGITS = "0123456789ABCDEF";

    /**
     * 移位计算
     */
    private static final int MOVE = 4;

    /**
     * 求与值
     */
    private static final int AND_TARGET = 0xf;

    /**
     * 根据字符串生成校验位
     * Date 2015年8月11日 下午2:29:31
     *
     * @param btInput 要生成校验位的字符串
     * @return 校验位
     */
    public static String getCheckCode(byte[] btInput) {
        byte result = btInput[0];
        for (int i = 1; i < btInput.length; i++) {
            result ^= btInput[i];
        }
        return String.valueOf(HEX_DIGITS.charAt(result >>> MOVE & AND_TARGET));
    }

    /**
     * 根据手机号生成token
     * Date 2015年8月13日 下午2:09:18
     *
     * @param phoneNo 手机号
     * @return 生成的token
     */
    public static String createToken(String phoneNo) {

        return AESTool.encrypt(phoneNo);
    }

    /**
     * 校验token是否有效
     * Date 2015年8月13日 下午2:18:12
     *
     * @param token token字符串
     * @return 校验通过, 返回电话号码 ,校验失败,返回ERROR
     */
    public static String getPhoneNoFromToken(String token) {
        Validate.notEmpty(token, "token can't be null!");
        return AESTool.decrypt(AESTool.KEY, token);
    }


}
