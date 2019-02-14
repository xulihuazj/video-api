package com.yinfeixing.video.api.client.video;

import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.video.api.BaseController;
import com.yinfeixing.video.api.request.APIRequest;
import com.yinfeixing.video.api.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.service.app.video.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/client/video")
public class VideoController extends BaseController {

    @Resource
    private VideoService videoServiceImpl;

    /**
     * 视频列表
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/list")
    public void videoList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoListRequest> apiRequest = super.getObjectByRequest(ClientVideoListRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.videoList(apiRequest), httpRequest, httpResponse);
    }

}
