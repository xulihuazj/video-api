package com.yinfeixing.video.service.impl.app.video;

import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.api.request.APIRequest;
import com.yinfeixing.video.api.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.api.response.APIResponse;
import com.yinfeixing.video.api.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.app.video.VideoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    private Logger logger = LogManager.getLogger(VideoServiceImpl.class);

    @Override
    public APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频列表】，请求参数={0}", request);



        return null;
    }
}
