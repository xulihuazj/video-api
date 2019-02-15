package com.yinfeixing.video.enums;

/**
 * APi返回码定义
 */
public enum ResponseStatusCodeEnum {

    SUCCESS("SUCCESS", "成功"),;

    /**
     * The Code.
     */
    private String code;

    /**
     * The Message.
     */
    private String message;

    ResponseStatusCodeEnum(String code, String message) {
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
