package com.yinfeixing.utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * UniqueId 生成
 */
public class UniqueGenerationUtil {

    /**
     * 生成UniqueId
     *
     * @param phone        电话
     * @param userId       用户ID
     * @param createTime   主键创建时间
     * @param businessCode 业务码
     * @param randomNo     随机码位数
     * @return UniqueId
     */
    public static String contractNumGenerate(String phone, Long userId, Date createTime, UniqueBusinessCode businessCode, Integer randomNo) {
        if (null == phone || null == createTime || null == businessCode || null == randomNo) {
            return null;
        } else {
            //取手机号后6位
            String formatKey = phone.substring(phone.length() - 6);
            long euid = userId % 2;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //order_id 生成规则：时间戳 +2位业务吗（01）+ 6位主键（000001）+1位（用户ID模2）+2位随机码
            String orderNo = businessCode.getCode() + formatKey + String.valueOf(euid) + String.valueOf(getRandom(randomNo));
            return dateFormat.format(createTime) + orderNo;
        }
    }

    private static String getRandom(int n) {
        Random random = new Random();
        String[] randomType = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            sb.append(randomType[random.nextInt(10)]);
        }
        return sb.toString();
    }

    /**
     * 生成UniqueId
     *
     * @param primaryKey   主键
     * @param userId       用户ID
     * @param createTime   主键创建时间
     * @param businessCode 业务码
     * @param randomNo     随机码位数
     * @return UniqueId
     */
    public static String orderNoGenerate(Long primaryKey, Long userId, Date createTime, UniqueBusinessCode businessCode, Integer randomNo) {
        if (null == primaryKey || null == createTime || null == businessCode || null == randomNo) {
            return null;
        } else {
            String formatKey = String.format("%06d", primaryKey);
            long euid = userId % 2;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //order_id 生成规则：时间戳 +2位业务吗（01）+6位主键（000001）+1位（用户ID模2）+2位随机码
            return dateFormat.format(createTime) + businessCode.getCode() + formatKey + String.valueOf(euid) + String.valueOf(getRandom(randomNo));
        }
    }
}
