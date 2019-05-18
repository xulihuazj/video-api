package com.yinfeixing.video.api.manage.video;

import com.yinfeixing.utils.validate.Add;
import com.yinfeixing.video.BaseController;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.manage.VideoManageService;
import com.yinfeixing.video.system.SystemType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/video")
public class VideoMangeController extends BaseController {

    @Resource
    private VideoManageService videoManageServiceImpl;

    /**
     * 视频更新
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @PostMapping("/update")
    @SystemType
    public void videoUpdate(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoListRequest> apiRequest = super.getObjectByRequest(ClientVideoListRequest.class,
                httpRequest, Add.class);
        super.success(videoManageServiceImpl.videoUpdate(apiRequest), httpRequest, httpResponse);
    }

    /**
     * 视频搜索
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @PostMapping("/search")
    @SystemType
    public void videoSearch(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientVideoListRequest> apiRequest = super.getObjectByRequest(ClientVideoListRequest.class,
                httpRequest, Add.class);
        super.success(videoManageServiceImpl.videoSearch(apiRequest), httpRequest, httpResponse);
    }


}
