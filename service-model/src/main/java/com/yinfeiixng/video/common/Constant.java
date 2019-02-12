package com.yinfeiixng.video.common;

/**
 * 统一常量
 *
 * @author xulh
 * @date 2018/03/05 13:58
 */
public class Constant {

    /**
     * map 初始化大小16
     */
    public static final Integer MAP_INIT_SIZE = 16;

    /**
     * 26字母表
     */
    public static final char[] ALPHABET_FOR_26 = {'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * Mapper 返回结果对比值
     */
    public static final Integer MAPPER_RESULT_DEFAULT_VALUE = 0;
    /**
     * 生成token时的参数key
     */
    public static final String TOKEN_KEY = "z-y*-$-M-_-#-a-B-S-s-o-P-O";
    /**
     * vacant_index 房间 空置指数 ： 默认值 100
     */
    public static final String  VACANTINDEX_DEFAULT_VALUE = "100";

   /**
    * pms用户信息过期时间
    */
   public static final int USERINFO_EXPIRATION_TIME = 3600;

    /**
    *  移动端用户信息过期时间 30天
    */
   public static final int EXTERNAL_EXPIRATION_TIME = 3600*24*30;


    /**
     *  常量值 -1
     */
    public static final int PARENT_ID = -1;

    /**
     *  常量值 1
     */
    public static final int PAGE_NO = 1;

    /**
     *  每页总数 10
     */
    public static final int PAGE_COUNT = 10;

    /**
     *   常量值 0
     */
    public static final int ZERO = 0;

    /**
     *  byte 0
     */
    public  static  final  byte BYTE=0;

    public static final String PMSUSERTOKEN = "PMSUSERTOKEN";

    /**
     *  常量值 root
     */
     public  static  final  String GROUP_CODE="root";

    /**
     *  常量
     */
    public  static  final  String  COMPARE="#";

    /**
     *  英文点号  .
     */
    public  static  final  String  POINT=".";

    /**
     *  破折号.
     */
    public  static  final  String  DASH="-";


    /**
     *  逗号
     */
    public  static  final  String  COMMA=",";

    /**
     *  权限码更改
     */
    public  static  final  String  PRIVIELGE_CHANGE="岗位权限更改";

    /**
     *  折扣审核权限
     */
    public  static  final  String  DISCOUNT_PRIVILEGE="所有折扣权限";
    
    /**
     * 图片验证码过期时间，单位为秒
     */
    public static final int IMG_CODE_OVERDUE_TIME = 60000;


    /**
     *  获取API url地址缓存KEY
     */
    public  static  final  String  API_USE_KEY="API_USE_KEY";

    /**
     *  云盯响应成功码
     */
    public  static  final  int  YUNDING_SUCCESS_CODE=1000;

    /**
     *  云盯响应成功
     */
    public  static  final  String  YUNDING_SUCCESS="成功";

    /**
     *  云盯响应失败
     */
    public  static  final  String  YUNDING_FAILED="失败";

    /**
     *  缓存品牌信息
     */
    public  static  final  String  BRAND_INFO="BRAND_INFO";
    
    /**
     * ip白名单配置过期时间为一小时（单位秒）
     */
    public static final int IP_WHITE_TIME = 3600;
    
    /**
     * ip白名单的key
     */
    public static final String IP_WHITE_KEY = "IP_WHITE_KEY_20180604";

    /**
     *  缓存项系统用户项目信息
     */
    public  static  final  String  PROJECT_INFO="PROJECT_INFO";

    /**
     * 缓存移动端用户token信息
     */
    public static final String EXTERNAL_INFO = "EXTERNAL_INFO";

    /**
     * 编号长度
     */
    public static final int MAX_NO = 1000000;

    /**
     * 预付账单充值凭证key
     */
    public static final String PREPAY_BALANCE_CERT_KEY = "PREPAY_BALANCE_CERT_KEY";

    /**
     * 合同模版ftl文件名
     */
    public static final String CONTRACT_FTL_NAME="contract.ftl";


    public static final Long DATE_HOUR_2 = Long.valueOf(1000 * 60 * 60 * 2);
    
    public static String API_BODY_KEY = "PMS_API_BODY_KEY";
    
    public static String IDEMPOTENT_KEY = "PMS_API_IDEMPOTENT_KEY";
    
    public static String IDEMPOTENT_UUID = "PMS_API_IDEMPOTENT_UUID";
    
    public static String H5_IMG_PAGE_KEY = "H5_IMG_PAGE_KEY";
    
    public static String TODAY_FIRST_NUMBER_SIZE = "TODAY_FIRST_NUMBER_SIZE";

    /**
     * traceid
     */
    public static String TRACE_ID = "traceid";
}
