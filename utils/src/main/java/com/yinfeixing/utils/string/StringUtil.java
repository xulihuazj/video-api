package com.yinfeixing.utils.string;

import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }

    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());

            for (int i = 1; i < camelCaseName.length(); ++i) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;

            for (int i = 0; i < underscoreName.length(); ++i) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else if (flag) {
                    result.append(Character.toUpperCase(ch));
                    flag = false;
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

    public static boolean isNotEmpty(String aStr) {
        return aStr != null && aStr.trim().length() > 0;
    }

    public static boolean isEmpty(String aStr) {
        return aStr == null || aStr.trim().length() == 0;
    }

    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+[.]?[\\d]*$");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static void trimBlank(String data) {
        System.out.println(data.trim());
    }

    public static void main(String[] args) {
        trimBlank((String) null);
    }
}
