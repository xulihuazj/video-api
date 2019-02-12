package com.yinfeiixng.video.enums;


public enum CommonStatusEnum {

    EFFECTIVE("EFFECTIVE", "有效"),

    INVALID("INVALID", "无效"),;

    String code;

    String desc;

    CommonStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}