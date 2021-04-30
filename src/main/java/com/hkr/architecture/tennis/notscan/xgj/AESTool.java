package com.hkr.architecture.tennis.notscan.xgj;



import com.hkr.architecture.tennis.TennisToolException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * ClassName: SecurityEncoderUtil
 * Function: 加密编解码工具类.
 * Date: 2015年9月16日 下午2:41:36
 *
 * @author fanjm
 * @since JDK 1.7
 */
public class AESTool {

    private AESTool() {
    }

    /**
     * 加密盐值
     */
    public static final String KEY = "korakora";

    /**
     * 转换16进制字符串
     */
    public static final String HEX = "0123456789ABCDEF";

    /**
     * AES加密.
     *
     * @param clearText 要加密的明文
     * @return 密文
     * @author fanjm
     * @since JDK 1.7
     */
    public static String encrypt(String clearText) throws TennisToolException {
        byte[] result;
        try {
            byte[] rawkey = getRawKey(KEY.getBytes());
            result = encrypt(rawkey, clearText.getBytes());
        } catch (Exception e) {
            throw new TennisToolException(e.getMessage(), e.getCause());
        }
        String content = toHex(result);
        return content;

    }

    /**
     * @param raw   二进制数组
     * @param clear 清空数组
     * @return encrypted 加密数组
     * @throws Exception 加密异常
     * @author Administrator
     * @since JDK 1.7
     */
    public static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(
                Cipher.ENCRYPT_MODE,
                skeySpec,
                new IvParameterSpec(new byte[cipher.getBlockSize()])
        );
        return cipher.doFinal(clear);
    }

    /**
     * AES解密.
     *
     * @param seed      盐值
     * @param encrypted 要解密的密文
     * @return 明文
     * @author fanjm
     * @since JDK 1.7
     */
    public static String decrypt(String seed, String encrypted) throws TennisToolException {
        byte[] rawKey;
        try {
            rawKey = getRawKey(seed.getBytes());
            byte[] enc = toByte(encrypted);
            byte[] result = decrypt(rawKey, enc);
            return new String(result);
        } catch (Exception e) {
            throw new TennisToolException(e.getMessage(), e.getCause());
        }

    }

    /**
     * AES解密.
     *
     * @param encrypted 要解密的密文
     * @return 明文
     * @author k_liu
     * @since JDK 1.7
     */
    public static String decrypt(String encrypted) throws TennisToolException {
        byte[] rawKey;
        try {
            rawKey = getRawKey(KEY.getBytes());
            byte[] enc = toByte(encrypted);
            byte[] result = decrypt(rawKey, enc);
            return new String(result);
        } catch (Exception e) {
            throw new TennisToolException(e.getMessage(), e.getCause());
        }

    }

    /**
     * @param raw       二进制数组
     * @param encrypted 加密
     * @return decrypted 解密数组
     * @throws Exception 解密异常
     * @author Administrator
     * @since JDK 1.7
     */
    public static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return cipher.doFinal(encrypted);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr);
        SecretKey secKey = kgen.generateKey();
        return secKey.getEncoded();
    }

    /**
     * byte数组转16进制字符串.
     *
     * @param buf byte数组
     * @return 字符串
     * @author fanjm
     * @since JDK 1.7
     */
    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuilder result = new StringBuilder(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    /**
     * 16进制字符串转byte数组.
     *
     * @param hexString 字符串
     * @return byte数组
     * @author fanjm
     * @since JDK 1.7
     */
    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    private static void appendHex(StringBuilder sb, byte byt) {
        sb.append(HEX.charAt((byt >> 4) & 0x0f)).append(HEX.charAt(byt & 0x0f));
    }

}
