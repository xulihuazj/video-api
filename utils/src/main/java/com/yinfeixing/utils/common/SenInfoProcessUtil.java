package com.yinfeixing.utils.common;

import org.apache.commons.lang3.StringUtils;

/**
 * SenInfoProcessUtil.java 1.0.0 2018-06-07  15:23
 * Copyright © 2014-2018,52mamahome.com.All rights reserved
 * history :
 * 1. 2018-06-07  15:23 @author
 *  敏感信息处理
 */
public class SenInfoProcessUtil {

    /**
      * @Author:
      * @Description: 手机号脱敏
      * @Date: 15:29 2018-06-07
      * @param phone
      * @return
      */
    public  static  String phoneEnct(String phone){
        if(StringUtils.isBlank(phone)){
            return null;
        }
        return  phone.substring(0,4)+"***"+phone.substring(phone.length()-4);
    }

    /**
      * @Author:
      * @Description: 证件号脱敏
      * @Date: 15:30 2018-06-07
      * @param cretNum
      * @return
      */
    public static String cretNumEnct(String cretNum){
        if(StringUtils.isBlank(cretNum)){
            return null;
        }
        if(cretNum.length()>5){
            StringBuffer stringBuffer=new StringBuffer(cretNum.substring(0,2));
            for(int i=0;i<cretNum.length()-5;i++){
                stringBuffer.append("*");
            }
            stringBuffer.append(cretNum.substring(cretNum.length()-3));
            return stringBuffer.toString();
        }else{
            return cretNum;
        }
    }

}
