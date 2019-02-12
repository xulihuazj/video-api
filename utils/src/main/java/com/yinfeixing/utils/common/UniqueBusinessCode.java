package com.yinfeixing.utils.common;

public enum UniqueBusinessCode {

    LEASE_PERSON("01", "个人租约"),

    LEASE_COMPANY("02", "企业租约"),

    CREATE_BILL_ORDER("03", "创建账单订单"),

    REFUND_ORDER("04", "退款订单创建"),

    CREATE_RECHANGE_ORDER("05", "创建充值订单"),

    INVOICE_NO_GE("06", "开票编号"),

            ;

    private String code;
    private String desc;

    UniqueBusinessCode(String code, String desc) {
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
