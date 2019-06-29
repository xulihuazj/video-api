package com.yinfeixing.video.service.app.video;

import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.*;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientTelevisionListResponse;
import com.yinfeixing.video.response.app.video.ClientVideoDetailResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.response.app.video.DicResponse;

public interface VideoService {

    /**
     * 视频列表
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> apiRequest);

    /**
     * 热门电影推荐列表
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoListResponse> videoRecommendList(APIRequest<ClientVideoRecommendRequest> apiRequest);

    /**
     * 视频详情
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoDetailResponse> videoDetail(APIRequest<ClientVideoDetailRequest> apiRequest);

    /**
     * 视频下载计数
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientVideoDetailResponse> videoDownloadIncrease(APIRequest<ClientVideoDetailRequest> apiRequest);


    /**
     * 基础字典查询
     *
     * @param apiRequest
     * @return
     */
    APIResponse<DicResponse> baseInfoSearch(APIRequest<DicRequest> apiRequest);


    /**
     * 电视剧列表
     *
     * @param apiRequest
     * @return
     */
    APIResponse<ClientTelevisionListResponse> televisionList(APIRequest<ClientTelevisionListRequest> apiRequest);


}
