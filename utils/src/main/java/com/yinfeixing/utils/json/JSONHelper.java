package com.yinfeixing.utils.json;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class JSONHelper {
    public JSONHelper() {
    }

    public static String toJson(Object object) {
        return object == null ? null : JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> tClass) {
        return StringUtils.isBlank(json) ? null : JSON.parseObject(json, tClass);
    }
}
