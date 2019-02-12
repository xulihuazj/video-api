package com.yinfeixing.utils.common;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 *     单位转换
 */
public class UnitConvertUtil {

    /**
    * 平方米和英尺转化
    * 1平方米(㎡)=10.76平方英尺
    * 1平方英尺(sq.ft)=0.09平方米(㎡)
    * convertUnit  转换后的面积单位
    */
    public static String areaConvert(String convertUnit, Double originArea) {
        if (StringUtils.isEmpty(convertUnit) || null == originArea) {
            return null;
        }
        // 保留两位小数点
        DecimalFormat df = new DecimalFormat("#.00");
        String tempArea = null;
        if (convertUnit.equalsIgnoreCase("METER")) {

            tempArea = String.valueOf(0.09 * originArea);

        } else if (convertUnit.equalsIgnoreCase("FEET")) {

            tempArea = String.valueOf(10.76 * originArea);

        }
        if (df.format(Double.parseDouble(tempArea)).length() <= 3) {
            return "0" + df.format(Double.parseDouble(tempArea));
        } else {
            return df.format(Double.parseDouble(tempArea));
        }
    }


    /**
     * 四舍五入 返回整数字符串
     */
    public static String getUpInteger(double d) {
        DecimalFormat df = new DecimalFormat("#0");
        return df.format(d);
    }

    /**
     * 去小数点
     */
    public static String getIntegerPrice(String data) {
        if (StringUtils.isBlank(data) || "0".equalsIgnoreCase(data)) {
            return "0";
        } else {
            int index = data.indexOf(".");
            return data.substring(0, index + 2);
        }
    }

    /**
     * 向上取整并且不要小数点
     */
    public static String getCeilPrice(String data) {
        if (StringUtils.isBlank(data)) {
            return "0";
        } else {
            return getIntegerPrice(String.valueOf(Math.ceil(Double.valueOf(data))));
        }
    }

    /**
     * 向下取整并且不要小数点
     */
    public static String getFloorPrice(String data) {
        if (StringUtils.isBlank(data)) {
            return "0";
        } else {
            return getIntegerPrice(String.valueOf(Math.floor(Double.valueOf(data))));
        }
    }

    /**
     * 判断是否是数字类型
     */
    public static boolean verificateAmount(String amount) {
        // Pattern pattern = Pattern.compile("[0-9]+^.[0-9]+");^[0-9]+$|^[0-9]+\.[0-9]{1,2}$
        Pattern pattern = Pattern.compile("^[0-9]+$|^[0-9]+\\.[0-9]+");

        return pattern.matcher(amount).matches();
    }

    /**
     * 保留两位小数点
     */
    public static String getTwoPoint(double d) {
        String data = String.valueOf(d);
        if (data.indexOf(".") > 0) {
            String[] dataArra = data.split("\\.");
            if (dataArra[1].length() >= 2) {
                return dataArra[0] + "." + dataArra[1].substring(0, 2);
            } else {
                return data + 0;
            }
        }
        return data + "0.00";
    }

    /**
     * 整数相除保留四位小数点
     * 第一个是分母
     * 第二个分母
     */
    public static String savePoint(int first, int second) {
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.0000");
        if (second == 0) {
            return "0";
        }
        return df.format(((float) first / second) * 100);
    }

    /**
     * 
    * @Function: UnitConvertUtil.java
    * @Description: divisor：除数,dividend:被除数,direction:1为向上2为向下,digits保留小数后的位数
    * @param: divisor：除数,dividend:被除数,direction:1为向上2为向下,digits保留小数后的位数
    * @return：String
    * @author: mazy
    * @date: 2018年3月13日 下午8:26:00
     */
    public static String division(BigDecimal divisor, BigDecimal dividend, int direction, int digits) {
        String results;
        if (direction == 1) {
            results = divisor.divide(dividend, digits, BigDecimal.ROUND_CEILING).toString();
        } else {
            results = divisor.divide(dividend, digits, BigDecimal.ROUND_FLOOR).toString();
        }
        return results;
    }

    /**
     * @Function: UnitConvertUtil.java
     * @Description: 乘法,multiplier：乘数,multiplicand:被乘数,direction:1为向上2为向下,digits保留小数后的位数
     * @return：String
     * @author: mazy
     * @date: 2018年3月13日 下午9:06:47
     */
    public static String mul(BigDecimal multiplier, BigDecimal multiplicand, int direction, int digits) {
        String results;
        BigDecimal product = multiplier.multiply(multiplicand);
        if (direction == 1) {
            results = product.divide(new BigDecimal("1.0"), digits, BigDecimal.ROUND_CEILING).toString();
        } else {
            results = product.divide(new BigDecimal("1.0"), digits, BigDecimal.ROUND_FLOOR).toString();
        }
        return results;
    }
    
    /**
     * 
     * @Description : 元转分 不进位
     * @Author :
     * @Date : 2018年7月30日 下午6:01:40
     * @param amount
     * @return
     */
    public static Long yuanToPointsNormLong(String amount){  
    	if(StringUtils.isNotBlank(amount)){
    		amount = amount.trim();
            int index = amount.indexOf(".");  
            int length = amount.length();  
            Long amLong = 0l;  
            if(index == -1){  
                amLong = Long.valueOf(amount+"00");  
            }else if(length - index >= 3){  
                amLong = Long.valueOf((amount.substring(0, index+3)).replace(".", ""));  
            }else if(length - index == 2){  
                amLong = Long.valueOf((amount.substring(0, index+2)).replace(".", "")+0);  
            }else{  
                amLong = Long.valueOf((amount.substring(0, index+1)).replace(".", "")+"00");  
            }  
            return amLong; 
    	}else{
    		return null;
    	}
    } 
    
    /**
     * 
     * @Description :  元转分 不进位
     * @Author : zsy
     * @Date : 2018年7月30日 下午6:02:16
     * @param amount
     * @return
     */
    public static Integer yuanToPointsNormInt(String amount){  
    	if(StringUtils.isNotBlank(amount)){
    		Integer amInt = yuanToPointsNormLong(amount).intValue();
            return amInt;
    	}else{
    		return null;
    	}
    } 
    
    /**
     * 
     * @Description :  元转分 不进位
     * @Author : zsy
     * @Date : 2018年7月30日 下午6:02:21
     * @param amount
     * @return
     */
    public static String yuanToPointsNormStr(String amount){  
    	if(StringUtils.isNotBlank(amount)){
    		String amStr = yuanToPointsNormLong(amount).toString();
            return amStr;  
    	}else{
    		return null;
    	}
    }
    
    /**
     * 
     * @Description : 分转元 
     * @Author : zsy
     * @Date : 2018年7月30日 下午6:02:28
     * @param amount
     * @return
     */
    public static String pointsToyuanNormStr(Long amount){
    	if(amount != null){
    		return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).toString();  
    	}else{
    		return null;
    	}
    }

}
