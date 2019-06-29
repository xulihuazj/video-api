package com.yinfeixing.video.api.client.video;

import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.video.BaseController;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientTelevisionListRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoRecommendRequest;
import com.yinfeixing.video.service.app.video.VideoService;
import com.yinfeixing.video.system.SystemType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 综艺节目
 */
@RestController
@RequestMapping(value = "/client/variety")
public class VarietyController extends BaseController {

    @Resource
    private VideoService videoServiceImpl;

    /**
     * 电视剧列表
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/list")
    public void varietyList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientTelevisionListRequest> apiRequest = super.getObjectByRequest(ClientTelevisionListRequest.class, httpRequest, Query.class);
        super.success(videoServiceImpl.televisionList(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 热门综艺推荐
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/list/hot")
    public void varietyHotList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoRecommendRequest> apiRequest = super.getObjectByRequest(ClientVideoRecommendRequest.class, httpRequest, Query.class);
        super.success(videoServiceImpl.videoRecommendList(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 电影详情
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/detail")
    @SystemType
    public void varietyDetail(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoDetailRequest> apiRequest = super.getObjectByRequest(ClientVideoDetailRequest.class, httpRequest, Query.class);
        super.success(videoServiceImpl.videoDetail(apiRequest), httpRequest, httpResponse);
    }

}
