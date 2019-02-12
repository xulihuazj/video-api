package com.yinfeixing.utils.uuid;

import java.util.UUID;

/**
 * UUID工具类
 * @author xulihua
 */
public class UUIDUtil {
    
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
