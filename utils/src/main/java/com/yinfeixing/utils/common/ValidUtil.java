package com.yinfeixing.utils.common;

import org.apache.commons.lang3.StringUtils;

public class ValidUtil {

    public static boolean validChinaPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        } else {
            String regex = "1[0-9]{10}$";
            return phone.matches(regex);
        }
    }

    public static boolean validEmail(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            String regex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            return str.matches(regex);
        }
    }

    /**
     * 校验VASA卡
     *
     * @param str
     * @return
     */
    public static boolean validVASA(String str) {
        if (StringUtils.isNotBlank(str)) {
            String regex = "^(4\\d{12}(?:\\d{3})?)$";
            return str.matches(regex);
        } else {
            return false;
        }
    }

    /**
     * 校验Master卡
     *
     * @param str
     * @return
     */
    public static Boolean validMaster(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            String regex = "^(5[0-5]\\d{2})[\\s\\-]?(\\d{4})[\\s\\-]?(\\d{4})[\\s\\-]?(\\d{4})$";
            return str.matches(regex);
        }
    }

    /**
     * 校验内部系统邮箱格式是否正确
     *
     * @param str
     * @return
     */
    public static boolean validSystemEmail(String str) {
        String regex = "^(\\w+((-\\w+)|(\\.\\w+))*\\@(52)?mamahome\\.com(.sg)?)$";
        return str.matches(regex);
    }

    public static boolean validOnWhiteList(String mail, String whiteList) {
        if (StringUtils.isBlank(mail) || StringUtils.isBlank(whiteList)) {
            return false;
        } else {
            String endMail = StringUtils.substringAfter(mail, "@");
            return whiteList.contains(endMail);
        }
    }

    /**
     * 银行卡校验
     */
    public static boolean matchLuhn(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param password
     * @return
     * @Description: 字母，数字，符号最少含有两种
     * @Date: 16:12 2018-05-31
     */
    public static boolean passwordValid(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        String match = "(?!(\\d+|[a-zA-Z]+|[_]+)$)\\w{6,20}";
        return password.matches(match);
    }

    /**
     * @param amount
     * @return
     * @Description: 校验是否是一个正确的金额： 只能包含两位小数点，03.33 不符合要求，整数前面不能含有0
     * @Date: 10:18 2018-06-27
     */
    public static boolean validAmount(String amount) {
        if (StringUtils.isBlank(amount)) {
            return false;
        }
        return amount.matches("([1-9]\\d*|0)(\\.\\d{1,2})?");
    }

    /**
     * @param nickName
     * @return
     * @Description: 中文，字母（区分大小写），- _ 不能为纯数字
     * @Date: 10:18 2018-06-27
     */
    public static boolean validNickName(String nickName) {
        if (StringUtils.isBlank(nickName)) {
            return false;
        }
        return nickName.matches("(?!([0-9]+)$)[\\u4e00-\\u9fa50-9a-zA-Z\\-_]{4,20}");
    }


    public static void main(String[] args) {
        String match = "(?!([0-9]+)$)[\\u4e00-\\u9fa50-9a-zA-Z\\-_]{4,20}";
        System.out.println(validAmount("0.333"));

    }

}
