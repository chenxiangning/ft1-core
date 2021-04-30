package com.hkr.architecture.tennis.notscan.mq;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-22 10:59
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
public class FT1AliMqProperties {
    public static final String Topic = "Topic";
    public static final String ProducerId = "ProducerId";
    public static final String ConsumerId = "ConsumerId";
    public static final String AccessKey = "AccessKey";
    public static final String SecretKey = "SecretKey";
    public static final String ONSAddr = "ONSAddr";

    public static final String OpenTheLogo = "OpenTheLogo";

    public static final String TagMsgPMS = "tag_pms";
    public static final String TagMsgSMS = "tag_sms";


    public static String mgMsgTags() {
        return String.format("%s||%s", TagMsgPMS, TagMsgSMS);
    }
}
