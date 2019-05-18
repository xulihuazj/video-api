package com.yinfeiixng.video.enums;

public enum AccountTypeEnum {

    PHONE("PHONE", "手机号"),

    WEIXIN("WEIXIN", "微信"),

    EMAIL("EMAIL", "邮箱"),

    ;

    private String code;

    private String message;

    AccountTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
