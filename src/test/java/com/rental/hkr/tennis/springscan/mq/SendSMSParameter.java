package com.rental.hkr.tennis.springscan.mq;

import com.rental.hkr.tennis.ValidatorTool;
import com.rental.hkr.tennis.notscan.gson.GsonTool;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class SendSMSParameter {

    @NotNull(message = "phoneNo不能为null")
    @NotEmpty(message = "phoneNo不能为空白")
    private String phoneNo;

    @NotNull(message = "短信模板不能为空")
    @NotEmpty(message = "短信模板不能为空白")
    private String templateCode;

    @NotNull(message = "短信模板不能为空 json格式的带替换变量,替换短信模板中的变量 例如:{'code':'1234'}")
    @NotEmpty(message = "短信模板不能为空白 json格式的带替换变量,替换短信模板中的变量 例如:{'code':'1234'}")
    private String templateVal;

    @NotNull(message = "签名不能为空 签名取值:氢氪出行")
    @NotEmpty(message = "签名不能为空白 签名取值:氢氪出行")
    private String signName = "氢氪出行";

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateVal() {
        return templateVal;
    }

    public void setTemplateVal(String templateVal) {
        this.templateVal = templateVal;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    @Override
    public String toString() {
        return "{" +
                "phoneNo='" + phoneNo + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", templateVal='" + templateVal + '\'' +
                ", signName='" + signName + '\'' +
                '}';
    }

    public String json() {
        ValidatorTool.validationBean(this);
        return GsonTool.objectToAllFieldNullJson(this);
    }
}
