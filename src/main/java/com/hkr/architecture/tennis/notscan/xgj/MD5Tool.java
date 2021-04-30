package com.hkr.architecture.tennis.notscan.xgj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenxiangning
 */
public class MD5Tool {
    private static MD5Tool instance = new MD5Tool();

    private long previous;

    protected MD5Tool() {
    }

    public static MD5Tool getInstance() {
        return instance;
    }

    public synchronized String generate(String msg, boolean timeChange) {
        try {

            long current = System.currentTimeMillis();
            if (current == previous) {
                current++;
            }
            previous = current;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            md.update("hkr-md5-key".getBytes());
            if (timeChange) {
                // byte now[] = (current+"").toString().getBytes();
                byte now[] = (new Long(current)).toString().getBytes();
                md.update(now);
            }
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
