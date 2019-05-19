package com.yinfeixing.video.api.client.video;

import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.video.BaseController;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.request.app.video.DicRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.app.video.VideoService;
import com.yinfeixing.video.system.SystemType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
     *
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

    /**
     * 视频详情
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/detail")
    @SystemType
    public void videoDetail(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoDetailRequest> apiRequest = super.getObjectByRequest(ClientVideoDetailRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.videoDetail(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 视频下载计数
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/download/increase")
    @SystemType
    public void videoDownloadIncrease(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoDetailRequest> apiRequest = super.getObjectByRequest(ClientVideoDetailRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.videoDownloadIncrease(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 基础字典数据搜索
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/dic/search")
    @SystemType
    public void baseInfoSearch(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<DicRequest> apiRequest = super.getObjectByRequest(DicRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.baseInfoSearch(apiRequest), httpRequest, httpResponse);
    }

}
