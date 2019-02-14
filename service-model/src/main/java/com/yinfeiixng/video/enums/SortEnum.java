package com.yinfeiixng.video.enums;

public enum SortEnum {

    _ID_ASC("_ID", "ASC"),

    _ID_DESC("_ID", "DESC"),

    _NAME_ASC("_NAME", "ASC"),

    _NAME_DESC("_NAME", "DESC"),
    ;


    String code;

    String desc;

    SortEnum(String code, String desc) {
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

