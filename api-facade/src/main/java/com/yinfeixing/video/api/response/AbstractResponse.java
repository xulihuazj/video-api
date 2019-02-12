package com.cf.api.response;

import com.cf.entity.ToString;

/**
 * api相应
 */
public class AbstractResponse extends ToString {

    /**
     * api返回码
     * 
     * @see com.cf.api.enums.ResponseStatusCodeEnum
     */
    private String statusCode;

    /** 消息 */
    private String message;

    /**
     * 当前线程执行id
     */
    private String traceId;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
