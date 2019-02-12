package com.yinfeixing.video.api.enums.system;


import com.yinfeixing.entity.BaseEnum;

/**
 * @author zhoujiahao on 2018-04-03.
 */
public enum SystemLogTypeEnum implements BaseEnum {

    DEPARTMENT_ADD("DEPARTMENT_ADD","添加部门"),

    DEPARTMENT_UPDATE("DEPARTMENT_UPDATE","修改部门"),

    DEPARTMENT_DELETE("DEPARTMENT_DELETE","删除部门"),

    USER_ADD("USER_ADD","添加用户"),

    USER_UPDATE("USER_UPDATE","修改用户"),

    USER_STATUS_TOGGLE("USER_STATUS_TOGGLE","用户状态切换"),

    POSITION_UPDATE("POSITION_UPDATE","修改岗位"),

    POSITION_ADD("POSITION_ADD","增加岗位"),

    POSITION_DELETE("POSITION_DELETE","删除岗位"),

    POSITION_PRIVILEGE_UPDATE("POSITION_PRIVILEGE_UPDATE","修改岗位权限")
    ;


    private String code;

    private String desc;

    SystemLogTypeEnum(String code, String desc) {
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
