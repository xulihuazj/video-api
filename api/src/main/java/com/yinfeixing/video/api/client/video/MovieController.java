package com.yinfeixing.video.api.client.video;

import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.video.BaseController;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.request.app.video.ClientVideoRecommendRequest;
import com.yinfeixing.video.service.app.video.VideoService;
import com.yinfeixing.video.system.SystemType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/client/movie")
public class MovieController extends BaseController {

    @Resource
    private VideoService videoServiceImpl;

    /**
     * 电影列表
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/list")
    public void movieList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoListRequest> apiRequest = super.getObjectByRequest(ClientVideoListRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.videoList(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 热门电影推荐
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/list/hot")
    public void videoRecommendList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoRecommendRequest> apiRequest = super.getObjectByRequest(ClientVideoRecommendRequest.class,
                httpRequest, Query.class);
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
    public void videoDetail(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoDetailRequest> apiRequest = super.getObjectByRequest(ClientVideoDetailRequest.class,
                httpRequest, Query.class);
        super.success(videoServiceImpl.videoDetail(apiRequest), httpRequest, httpResponse);
    }
}