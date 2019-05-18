package com.yinfeixing.utils.image;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

/**
 * 将文件上传至阿里云服务器
 * 图片
 */
public final class UploadImageUtil {

    private final static Logger logger = LogManager.getLogger(UploadImageUtil.class);

    //客户端对象
    private static volatile OSSClient ossClient = null;

    private static Object lockHelper = new Object();

    // 设置无参私有构造器，让该类不能通过new关键字来创建（请勿添加其它的构造器）
    private UploadImageUtil() {

    }

    private static OSSClient getOssClient(String endpoint, String accessKeyId, String accessKeySecret) {
        if (ossClient == null) {
            synchronized (lockHelper) {
                if (ossClient == null) {
                    // 使用系统默认的连接池设置
                    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    /**
     * accessKeyId 账户key accessKeySecret 加密秘钥 bucketName bucket名称 fileName 文件将名称
     * endpoint 域名地址 folder 存放在阿里云上面的文件夹名称
     */
    public static String uploadFileSource(String accessKeyId, String accessKeySecret, String bucketName, InputStream source,
                                          String contentType, String fileName, String endpoint, String folder) throws IOException {
        InputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 获得OSSClient对象
            getOssClient(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = source;
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            // 图片类型
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, objectMetadata);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.add(Calendar.SECOND, 30);
            URL url = ossClient.generatePresignedUrl(bucketName, folder + fileName, currentCalendar.getTime());
            fis = putResult.getCallbackResponseBody();
            if (fis != null) {
                // 用一个读输出流类去读
                isr = new InputStreamReader(fis);
                // 用缓冲器读行
                br = new BufferedReader(isr);
                String line = null;
                // 直到读完为止
                while ((line = br.readLine()) != null) {
                    LogHelper.debug(line);
                }
            }
            return putResult.getETag();
        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return null;
    }

    /**
     * 文件上传
     * 2018年5月15日 下午3:48:47
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @param source
     * @param fileName
     * @param endpoint
     * @param folder
     * @param contentType
     * @return
     * @throws IOException
     */
    public static String uploadSource(String accessKeyId, String accessKeySecret, String bucketName,
                                      InputStream source, String fileName, String endpoint, String folder, String contentType)
            throws IOException {
        InputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 获得OSSClient对象
            getOssClient(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = source;
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            if (StringUtil.isEmpty(contentType)) {
                objectMetadata.setContentType("image/jpeg");
            } else {
                objectMetadata.setContentType(contentType.trim());
            }
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, objectMetadata);
            fis = putResult.getCallbackResponseBody();
            if (fis != null) {
                // 用一个读输出流类去读
                isr = new InputStreamReader(fis);
                // 用缓冲器读行
                br = new BufferedReader(isr);
                String line = null;
                // 直到读完为止
                while ((line = br.readLine()) != null) {
                    LogHelper.debug(line);
                }
            }
            return putResult.getETag();
        } catch (Exception e) {
            LogHelper.error(e);
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return null;
    }

    /**
     * 下载文件，Stream需要调用方关闭
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName      存储空间
     * @param endpoint
     * @param fileName        文件名
     * @param folder          文件目录
     * @return
     */
    public static InputStream downloadFileSource(String accessKeyId, String accessKeySecret, String bucketName, String endpoint, String fileName, String folder) {
        // 获得OSSClient对象
        OSSClient ossClient = getOssClient(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, folder + fileName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream contentStream = ossObject.getObjectContent();
        return contentStream;
    }

    /**
     * accessKeyId 账户key
     * accessKeySecret 加密秘钥
     * bucketName  bucket名称
     * folder     存放文件夹
     * fileName   文件将名称
     * endpoint   域名地址
     */
    public static void deletePic(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, String fileName, String folder) {
        //获得OSSClient对象
        getOssClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, folder + fileName);
        ossClient.shutdown();
        ossClient = null;
    }

    /**
     * 判断OSS服务文件上传时文件的contentType
     *
     * @param contentType 文件类型
     * @return String
     */
    public static String getContentType(String contentType) {
        if (StringUtils.isNotBlank(contentType)) {
            contentType = contentType.toLowerCase();
            if (contentType.contains("bmp")) {
                return "image/bmp";
            } else if (contentType.contains("gif")) {
                return "image/gif";
            } else if (contentType.contains("jpeg") ||
                    contentType.contains("jpg") ||
                    contentType.contains("png") ||
                    contentType.contains("jpz")) {
                return "image/jpeg";
            } else if (contentType.contains("html") ||
                    contentType.contains("htm") ||
                    contentType.contains("hts")) {
                return "text/html";
            } else if (contentType.contains("txt")) {
                return "text/plain";
            } else if (contentType.contains("vsd")) {
                return "application/vnd.visio";
            } else if (contentType.contains("pptx") ||
                    contentType.contains("ppt")) {
                return "application/vnd.ms-powerpoint";
            } else if (contentType.contains("docx") ||
                    contentType.contains("doc")) {
                return "application/msword";
            } else if (contentType.contains("xml")) {
                return "text/xml";
            } else if (contentType.contains("xls")) {
                return "application/vnd.ms-excel";
            } else if (contentType.contains("xlsx")) {
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else if (contentType.contains("zip")) {
                return "application/zip";
            } else if (contentType.contains("pdf")) {
                return "application/pdf";
            } else if (contentType.contains("swf")) {
                return "application/x-shockwave-flash";
            }
        }
        return null;
    }
}
