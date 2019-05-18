package com.yinfeixing.video.enums.system;
/*
 * SystemTypeEnum.java 2017/7/12 15:57
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *    2017/7/12 15:57created by liumi
 */

public enum SystemApiTypeEnum {

    COMMON("common", "不需要校验", "common"),

    NOTIFY("NOTIFY", "支付异步通知特殊处理", "notify"),

    INTERNAL("internal", "内部系统", "internalValidPrivilege"),

    EXTERNAL("external", "外部系统", "externalValidPrivilege"),

    ;

    SystemApiTypeEnum(String code, String desc, String privilegeObjectName) {
        this.code = code;
        this.desc = desc;
        this.privilegeObjectName = privilegeObjectName;
    }

    private String code;
    private String desc;
    private String privilegeObjectName;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrivilegeObjectName() {
        return privilegeObjectName;
    }

}
