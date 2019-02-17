package com.yinfeixing.video.service.manage;

import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;

public interface VideoManageService {

    /**
     * 视频添加
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoUpdate(APIRequest<ClientVideoListRequest> apiRequest);


}
