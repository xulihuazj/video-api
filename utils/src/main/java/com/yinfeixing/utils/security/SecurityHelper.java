package com.yinfeixing.utils.security;


import com.yinfeixing.utils.log.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


/**
 * SecurityHelper
 *
 * @author xulh
 * @date 16/8/10
 * @description 加解密相关方法帮助类
 */
public class SecurityHelper {

    private static Logger logger = LogManager.getLogger(SecurityHelper.class);

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static boolean initialized = false;

    /**
     * DES解密方法
     *
     * @param message 密文
     * @param key     密钥
     * @return
     */
    public static String desDecrypt(String message, String key) {
        try {
            message = URLEncoder.encode(message, DEFAULT_ENCODING);
            byte[] bytesrc = convertHexString(message);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(DEFAULT_ENCODING));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(DEFAULT_ENCODING));

            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] retByte = cipher.doFinal(bytesrc);
            return new String(retByte);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }


    /**
     * aes解密
     */
    public static String aes256Decode(byte[] bytes, byte[] key) {
        initialize();
        byte[] result = null;
        try {
            SecretKeySpec key_spec = new SecretKeySpec(key, "AES");
            // 设置解密模式为AES的EBC模式
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key_spec);
            result = cipher.doFinal(bytes);
        } catch (Exception e) {
            LogHelper.exception(e, logger, "aes解密失败");
        }
        try {
            return new String(result, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    /**
     * DES加密方法
     *
     * @param message 明文
     * @param key     加密密钥
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String message, String key)
            throws Exception {

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(DEFAULT_ENCODING));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(DEFAULT_ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return URLDecoder.decode(toHexString(cipher.doFinal(message.getBytes(DEFAULT_ENCODING))), DEFAULT_ENCODING);
    }

    private static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

    private static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    /**
     * MD5加密
     *
     * @param md5Str 要进行md5加密的字符串
     * @return 返回加密后的md5串  如果异常则返回null
     */
    public static String MD5(String md5Str) {
        return Digest(md5Str, DigestEnum.MD5, null);
    }

    public static String MD5Utf(String md5Str, String charset) {
        return Digest(md5Str, DigestEnum.MD5, charset);
    }

    /**
     * SHA1加密 默认全部转换成小写 如需大写请使用重载传参数
     *
     * @param sha1Str
     * @return
     */
    public static String SHA1(String sha1Str) {
        return SHA1(sha1Str, true);
    }

    /**
     * SHA1加密 根据指定是否将结果转换为小写
     *
     * @param sha1Str
     * @param isToLower 是否转换小写
     * @return
     */
    public static String SHA1(String sha1Str, boolean isToLower) {
        String digeStr = Digest(sha1Str, DigestEnum.SHA1, null);
        if (digeStr != null && isToLower) {
            digeStr = digeStr.toLowerCase();
        }
        return digeStr;
    }

    public static String SHA512(final String shaStr) {
        return Digest(shaStr, DigestEnum.SHA512, null);
    }

    enum DigestEnum {
        MD5,
        SHA1,
        SHA512,
    }

    /**
     * 生成数据摘要
     *
     * @param digStr
     * @param type
     * @return
     */
    private static String Digest(String digStr, DigestEnum type, String charset) {
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = null;
            if (charset != null && charset.equalsIgnoreCase("UTF-8")) {
                btInput = digStr.getBytes();
            } else {
                btInput = digStr.getBytes("UTF-8");
            }
            //获取md5摘要获取对象
            MessageDigest md5Dig = null;//MessageDigest.getInstance("MD5");
            //DigestEnum de=//DigestEnum.SHA1;
            if (type == DigestEnum.MD5) {
                md5Dig = MessageDigest.getInstance("MD5");
            } else if (type == DigestEnum.SHA1) {
                md5Dig = MessageDigest.getInstance("SHA-1");
            } else if (type == DigestEnum.SHA512) {
                md5Dig = MessageDigest.getInstance("SHA-512");
            }
            byte[] md5Bytes = md5Dig.digest(btInput);
            StringBuilder sb = new StringBuilder();
            for (byte b : md5Bytes) {
                char c = hexDigits[(b >>> 4) & 0xf];
                sb.append(c);
                c = hexDigits[b & 0xf];
                sb.append(c);

            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void initialize() {
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
    
    /**
     *  利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public  static String decode(String decodeData){
        if(StringUtils.isBlank(decodeData)){
            return  null;
        }
        try {
            decodeData=  URLDecoder.decode(decodeData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeData;
    }

    public  static String encode(String data){
        if(StringUtils.isBlank(data)){
            return  null;
        }
        try {
            data=  URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(desEncrypt("{\"appId\":\"wx2e33be8771859aae\",\"mchId\":\"1509160431\",\"key\":\"lvjulogin5252100MMHLoGINddafda24\",\"publicKeySecret\":\"8e1d9b90e81ce5851d34d428b1b9a38a\",\"nativeAppId\":\"wxe1029b92873ea0b3\",\"nativeMchId\":\"1511049921\"}","12345678"));
        System.out.println(desDecrypt(desEncrypt("{\"appId\":\"wx2e33be8771859aae\",\"mchId\":\"1509160431\",\"key\":\"lvjulogin5252100MMHLoGINddafda24\",\"publicKeySecret\":\"8e1d9b90e81ce5851d34d428b1b9a38a\",\"nativeAppId\":\"wxe1029b92873ea0b3\",\"nativeMchId\":\"1511049921\"}","12345678"),"12345678"));
}
}

