package com.yinfeiixng.video.enums;

import com.yinfeixing.entity.BaseEnum;

public enum PasswordTypeEnum implements BaseEnum {

    LOGIN("LOGIN", "登录"),
    ;

    private String code;

    private String desc;

    PasswordTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}
