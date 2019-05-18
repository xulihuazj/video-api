package com.yinfeixing.utils.common;

import java.math.BigDecimal;

/**
 * NumberAmountToChinese.java 1.0.0 2018-07-26  17:13
 * Copyright © 2014-2018,52mamahome.com.All rights reserved
 * history :
 * 1. 2018-07-26  17:13 @author  xulh
 * 数字金额转成中文大写
 */
public class NumberAmountToChinese {
    /**
     * 大写数字规则
     * 中文大写金额数字应用正楷或行书填写，如壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整（正）等字样。不得用一、二（两）、三、四、五、六、七、八、九、十、廿、毛、另（或０）填写，不得自造简化字。如果金额数字书写中使用繁体字，如贰、陆、亿、万、圆的，也可。
     * 中文大写金额数字到"元"为止的，在"元"之后，应写"整"（或"正"）字，在"角"之后，可以不写"整"（或"正"）字。大写金额数字有"分"的，"分"后面不写"整"（或"正"）字。
     * 中文大写金额数字前应标明"人民币"字样，大写金额数字有"分"的，"分"后面不写"整"（或"正"）字。
     * 中文大写金额数字前应标明"人民币"字样，大写金额数字应紧接"人民币"字样填写，不得留有空白。大写金额数字前未印"人民币"字样的，应加填"人民币"三字。在票据和结算凭证大写金额栏内不得预印固定的"仟、佰、拾、万、仟、佰、拾、元、角、分"字样。
     * 阿拉伯数字小写金额数字中有"0"时，中文大写应按照汉语语言规律、金额数字构成和防止涂改的要求进行书写。举例如下：
     * 阿拉伯数字中间有"0"时，中文大写要写"零"字，如￥1409.50，应写成人民币壹仟肆佰零玖元伍角。
     * 阿拉伯数字中间连续有几个"0"时，中文大写金额中间可以只写一个"零"字，如￥6007.14，应写成人民币陆仟零柒元壹角肆分。
     * 阿拉伯金额数字万位和元位是"0"，或者数字中间连续有几个"0"，万位、元位也是"0"，但千位、角位不是"0"时，中文大写金额中可以只写一个零字，也可以不写"零"字。如￥1680.32，应写成人民币壹仟陆佰捌拾元零叁角贰分，或者写成人民币壹仟陆佰捌拾元叁角贰分，又如￥107000.53，应写成人民币壹拾万柒仟元零伍角叁分，或者写成人民币壹拾万零柒仟元伍角叁分。
     * 阿拉伯金额数字角位是"0"，而分位不是"0"时，中文大写金额"元"后面应写"零"字。如￥16409.02，应写成人民币壹万陆仟肆佰零玖元零贰分；又如￥325.04，应写成人民币叁佰贰拾伍元零肆分。
     */
    //大写
    static final String big = "零壹贰叁肆伍陆柒捌玖";
    //单位
    static final String[] units = {"仟佰拾", "角分"};


    /**
     * 双精度浮点数转换成字符串
     * 注：
     * 1、如果直接用String.toString(double d)方法，超大数额会出现科学计数法的字符串；
     * 2、如果整数部分超过15位数，低位部分可能出现误差，所以不支持超过15位整数的数值，
     * 一般数据库设计金额字段时都不会超过15位整数，如oracle用Number(18,3)的类型表示，整数部分最多15位，小数点后保留3位有效数字。
     */
    public static String getDecimalStr(double d) {
        //设置小数点后的精度，保留两位
        /*四舍五入结果参考:
        0.005,//0.01入
        0.015,//0.01舍
        0.025,//0.03入
        0.035,//0.04入
        0.045,//0.04舍
        0.055,//0.06入(前一位是5则入)
        */
        String str = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        //如果结果是整数，则去掉尾巴
        if (str.endsWith(".00")) {
            str = str.replace(".00", "");
        }
        return str;
    }

    /**
     * 金额是double类型的要先转换成字符串
     *
     * @param money 金额
     */
    public static String transform(double money) {
        String moneyStr = getDecimalStr(money);
        return transform(moneyStr);
    }

    /**
     * 金额转换成大字
     * 我的思路：
     * 1、double数值转换成数值字符串
     * 2、处理整数部分：
     * 填充到16位，不足16位则前面补'0'，然后右起分成四组，每组根据"x仟x佰x拾x"的规则转换成大写，若该组为"0000"则结果是"零"；
     * 对这四组结果从高位到低位拼接起来，规则：[组4]万[组3]亿[组2]万[组1]圆。
     * 3、处理小数部分（不多说）
     */
    public static String transform(String moneyStr) {
        //区别整数、小数部分
        String[] parts = moneyStr.split("\\.");
        String result = "";

        //整数部分的位数
        int length = parts[0].length();
        if (length > 15) {
            return "金额太大,不能处理整数部分超过15位的金额！";
        }
        String intPart = parts[0];

        //填充到16位，因为是分4组，每组4个数字
        while (intPart.length() < 16) {
            intPart = '0' + intPart;
        }
        //共分四组,右起四位一组,例如：0001,2003,0030,3400

        String[] groups = new String[4];
        for (int i = 0; i < groups.length; i++) {
            //开始位置
            int start = intPart.length() - (i + 1) * 4;
            //结束位置
            int end = intPart.length() - i * 4;
            groups[i] = intPart.substring(start, end);
            //当前组的四位数字转换成大写
            groups[i] = transformGroup(groups[i]);
        }

        //对这四组结果从高位到低位拼接起来，规则：[组4]万[组3]亿[组2]万[组1]圆
        for (int i = groups.length - 1; i >= 0; i--) {
            if (i == 3) {
                //第四组：万亿级
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "万";
                }
            } else if (i == 2) {
                //第三组：亿级
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "亿";
                } else {
                    if (result.length() > 0) {
                        result += "亿";
                    }
                }
            } else if (i == 1) {
                //第二组：万级
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "万";
                } else if (!groups[i].startsWith("零")) {
                    result += groups[i];
                }
            } else {
                //第一组：千级
                if (!"零".equals(groups[i]) || result.length() == 0) {
                    result += groups[i];
                }
                result += "圆";
            }
        }
        if (!"零圆".equals(result) && result.startsWith("零")) {
            //最前面的可能出现的“零”去掉//最前面的可能出现的“零”去掉
            result = result.substring(1, result.length());
        }

        //处理小数部分
        if (parts.length == 2) {
            //小数部分
            String decimalPart = parts[1];
            for (int i = 0; i < decimalPart.length(); i++) {
                //提取数字，左起
                int num = Integer.valueOf(decimalPart.charAt(i) + "");
                //数字变大写加上单位
                result += big.charAt(num) + "" + units[1].charAt(i);
            }
            //去掉"零角"的"角"
            result = result.replace("零角", "零");
            //去掉"零分"
            result = result.replace("零分", "");
        } else {
            //没有小数部分，则加上“整”
            result += "整";
        }

        return result;
    }

    /**
     * 处理整数部分的组，右起每四位是一组
     *
     * @param group 四位数字字符串
     */
    public static String transformGroup(String group) {
        String result = "";
        int length = group.length();
        for (int i = 0; i < length; i++) {
            //单个数字，左起
            int digit = Integer.valueOf(group.charAt(i) + "");
            //单位
            String unit = "";
            if (i != length - 1) {
                unit = units[0].charAt(i) + "";
            }
            //数字变大写加上单位
            result += big.charAt(digit) + unit;
        }

        result = result.replace("零仟", "零");
        result = result.replace("零佰", "零");
        result = result.replace("零拾", "零");

        while (result.contains("零零")) {
            //如果有“零零”则变成一个“零”
            result = result.replace("零零", "零");
        }

        if (!"零".equals(result) && result.endsWith("零")) {
            //最未尾的可能出现的“零”去掉
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("测试金额 " + "：" + transform(Double.valueOf("20003.44")));
    }

}
