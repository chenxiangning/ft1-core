package com.reachauto.hkr.tennis.notscan.mq.msg;

import com.reachauto.hkr.tennis.ValidatorTool;
import com.reachauto.hkr.tennis.notscan.gson.GsonTool;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PMSParameter {

    public static final String TITLE = "氢氪出行";

    @NotNull(message = "设备类型不能为空")
    @NotEmpty(message = "设备类型不能为空白")
    @Pattern(regexp = "[124]", message = "deviceType值只能为 1 2 4")
    private String deviceType;

    @NotNull(message = "设备编码不能为空")
    @NotEmpty(message = "设备编码不能为空白")
    private String deviceNo;

    @Pattern(regexp = "[124]", message = "消息类型取值:1,2,4 分别为 跳转优惠卷主页/跳转消息列表/其他跳转到地图 默认值为2")
    private String messageType = "2";

    @NotNull(message = "message不能为空")
    @NotEmpty(message = "message不能为空白")
    private String message;

    @Pattern(regexp = "\\d+", message = "userId只能为数字字符串")
    private String userId;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isAndroid() {
        return "1".equals(deviceType);
    }

    public boolean isIos() {
        return "2".equals(deviceType);
    }

    public boolean isPad() {
        return "4".equals(deviceType);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String json() {
        ValidatorTool.validationBean(this);
        return GsonTool.objectToAllFieldNullJson(this);
    }

    @Override
    public String toString() {
        return "UmengPushParameter{" +
                "deviceType='" + deviceType + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", messageType='" + messageType + '\'' +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
