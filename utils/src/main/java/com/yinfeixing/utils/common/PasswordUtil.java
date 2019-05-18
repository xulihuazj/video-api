package com.yinfeixing.utils.common;

import org.apache.commons.lang3.StringUtils;

/**
 * PasswordUtil.java 1.0.0 2018-03-08  13:32
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018-03-08  13:32 @author
 */
public class PasswordUtil {

    private static final String passWordRegx = "^[0-9A-Za-z_]{6,18}$";

    public static boolean passWordValid(String passWord) {

        if (StringUtils.isNotBlank(passWord)) {
            return passWord.matches(passWordRegx);
        }
        return false;
    }

}
