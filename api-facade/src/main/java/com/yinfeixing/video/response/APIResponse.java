package com.yinfeixing.video.response;

import com.yinfeixing.video.enums.ResponseStatusCodeEnum;

public class APIResponse<T> extends AbstractResponse {

    private T bizResponse;

    public static <T> APIResponse<T> instance(T res) {
        APIResponse<T> response = new APIResponse<T>();
        response.setBizResponse(res);
        response.setStatusCode(ResponseStatusCodeEnum.SUCCESS.getCode());
        response.setMessage(ResponseStatusCodeEnum.SUCCESS.getMessage());
        return response;
    }

    public T getBizResponse() {
        return bizResponse;
    }

    public void setBizResponse(T bizResponse) {
        this.bizResponse = bizResponse;
    }
}
