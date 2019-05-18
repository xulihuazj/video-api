package com.yinfeixing.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static List<String> list = new LinkedList();

    public static void getAllKey(Map<String, String> map, JSONObject jsonObject) {
        Iterator keys = jsonObject.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (isJsonObject(jsonObject.get(key).toString())) {
                JSONObject innerObject = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get(key)));
                getAllKey(map, innerObject);
            } else if (isArrayOrObject(jsonObject.get(key).toString())) {
                getKeyByList(map, jsonObject.get(key).toString());
            } else if (StringUtils.isNotBlank(jsonObject.get(key).toString())) {
                map.put(key, jsonObject.get(key).toString());
            }
        }

    }

    private static void getKeyByList(Map<String, String> map, String jsonArrayStr) {
        if (jsonArrayStr != null && isArrayOrObject(jsonArrayStr)) {
            JSONArray jsonArray = JSONArray.parseArray(jsonArrayStr);

            for (int j = 0; j < jsonArray.size(); ++j) {
                if (StringUtils.isNotBlank(jsonArray.get(j).toString())) {
                    if (isJsonObject(jsonArray.get(j).toString())) {
                        JSONObject innerObject = JSONObject.parseObject(jsonArray.get(j).toString());
                        getAllKey(map, innerObject);
                    } else if (isArrayOrObject(jsonArray.get(j).toString())) {
                        getKeyByList(map, jsonArray.get(j).toString());
                    }
                }
            }
        }

    }

    public static Boolean isJsonObject(String jsonString) {
        try {
            JSONObject.parseObject(jsonString);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static Boolean isArrayOrObject(String jsonObject) {
        try {
            return "[".equals(jsonObject.trim().substring(0, 1)) && "]".equals(jsonObject.trim().substring(jsonObject.trim().length() - 1, jsonObject.trim().length())) ? true : false;
        } catch (Exception var2) {
            return false;
        }
    }
}