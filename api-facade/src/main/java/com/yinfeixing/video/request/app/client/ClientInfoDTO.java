package com.yinfeixing.video.request.app.client;

/**
 * ClientInfoModel
 *
 * @author xulh
 * @date 2017/3/29
 * @description
 */
public class ClientInfoDTO {

    /**
     * 原始客户端的IP
     */
    private String originalClientIp;

    /**
     * 原始客户端的标志（浏览器为UserAgent,APP为设备ID)
     */
    private String originalClientSymbol;


    /**
     * 请求的客户端IP
     */
    private String clientIp;

    /**
     * 请求的客户端标志
     */
    private String clientSymbol;

    /**
     * 客户端Token
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientSymbol() {
        return clientSymbol;
    }

    public void setClientSymbol(String clientSymbol) {
        this.clientSymbol = clientSymbol;
    }

    /**
     * 获取原始客户端的IP
     *
     * @return
     */
    public String getOriginalClientIp() {
        return originalClientIp;
    }

    /**
     * 设置原始客户端的IP
     *
     * @param originalClientIp
     */
    public void setOriginalClientIp(String originalClientIp) {
        this.originalClientIp = originalClientIp;
    }

    /**
     * 获取原始客户端的标志（浏览器为UserAgent,APP为设备ID)
     *
     * @return
     */
    public String getOriginalClientSymbol() {
        return originalClientSymbol;
    }

    /**
     * 设置原始客户端的标志（浏览器为UserAgent,APP为设备ID)
     *
     * @param originalClientSymbol
     */
    public void setOriginalClientSymbol(String originalClientSymbol) {
        this.originalClientSymbol = originalClientSymbol;
    }
}
