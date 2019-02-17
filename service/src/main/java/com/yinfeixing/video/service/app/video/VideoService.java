package com.yinfeixing.video.service.app.video;

import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoDetailResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;

public interface VideoService {


    /**
     * 视频列表
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> apiRequest);

    /**
     * 视频详情
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoDetailResponse> videoDetail(APIRequest<ClientVideoDetailRequest> apiRequest);


    /**
     * 视频下载计数
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoDetailResponse>  videoDownloadIncrease(APIRequest<ClientVideoDetailRequest> apiRequest);
}
