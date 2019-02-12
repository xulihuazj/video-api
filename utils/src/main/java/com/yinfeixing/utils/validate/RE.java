package com.yinfeixing.utils.validate;

public final class RE {
    /**
     * 手机号码
     */
    public final static String MOBILE = "1[0-9]{10}$";
    /**
     * email
     */
    public final static String MAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    /**
     * password 数字字母字符_
     */
    public final static String PASSOWRD = "^[0-9A-Za-z_]{6,18}$";

    /**
     *
     */
    public final static String NUM_CHAR = "^[0-9A-Za-z]{1,18}$";

    /**
     *
     */
    public final static String UNIQUETAG = "^(.*){32}$";

    /**
     * APP 密码规则
     */
    public final static String APP_PASSWORD = "(?!(\\d+|[a-zA-Z]+|[_]+)$)\\w{6,20}";

    /**
     * 租金规则，最多2位小数
     */
    public static final String RENT_PRICE = "^\\d+(?:\\.\\d{1,2})?$";
}

