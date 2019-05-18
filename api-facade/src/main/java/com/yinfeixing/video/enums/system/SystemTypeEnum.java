package com.yinfeixing.video.enums.system;


import com.yinfeixing.entity.BaseEnum;

/**
 * @author zhoujiahao on 2018-04-03.
 */
public enum SystemTypeEnum implements BaseEnum {
    DEPARTMENT("DEPARTMENT", "部门"),

    USER("USER", "用户"),

    POSITION("POSITION", "岗位");


    private String code;

    private String desc;

    SystemTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
