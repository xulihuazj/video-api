package com.yinfeixing.video.service.manage;

import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;

public interface VideoManageService {

    /**
     * 视频添加
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoUpdate(APIRequest<ClientVideoListRequest> apiRequest);


    /**
     * 视频搜索
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoSearch(APIRequest<ClientVideoListRequest> apiRequest);


    /**
     * 视频详情
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoDetail(APIRequest<ClientVideoListRequest> apiRequest);


}
