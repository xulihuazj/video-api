package com.yinfeixing.video.api.request;

public class APIRequest<T> extends AbstractRequest {

    /**
     * 业务请求
     */
    private T bizRequest;

    public T getBizRequest() {
        return bizRequest;
    }

    /**
     * 根据类型获取bizRequest
     */
    public T getBizRequest(Class bizRequestClass) {
        if (bizRequest != null && bizRequest.getClass().equals(bizRequestClass)) {
            return bizRequest;
        }
        return null;
    }

    public static <T> APIRequest<T> instance(AbstractRequest originRequest, T bizRequest) {

        APIRequest<T> request = new APIRequest<T>();
        request.setToken(originRequest.getToken());
        request.setUserId(originRequest.getUserId());
        request.setDeviceId(originRequest.getDeviceId());
        request.setDtMonitor(originRequest.getDtMonitor());
        request.setSource(originRequest.getSource());
        request.setVersion(originRequest.getVersion());
        request.setBizRequest(bizRequest);
        return request;
    }

    public void setBizRequest(T bizRequest) {
        this.bizRequest = bizRequest;
    }
}
