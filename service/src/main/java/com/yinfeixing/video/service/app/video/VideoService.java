package com.yinfeixing.video.service.app.video;

import com.yinfeixing.video.api.request.APIRequest;
import com.yinfeixing.video.api.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.api.response.APIResponse;
import com.yinfeixing.video.api.response.app.video.ClientVideoListResponse;

public interface VideoService {

    /**
     * 视频列表
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> apiRequest);


}
