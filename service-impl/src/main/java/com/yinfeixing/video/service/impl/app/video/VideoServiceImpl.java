package com.yinfeixing.video.service.impl.app.video;

import com.yinfeiixng.video.model.mongo.VideoImageModel;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.core.BaseMongoRepository;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoDetailResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.app.video.VideoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private Logger logger = LogManager.getLogger(VideoServiceImpl.class);

    @Resource
    private VideoMongoRepository videoMongoRepositoryImpl;
    @Resource
    private BaseMongoRepository baseMongoRepositoryImpl;
//    @Resource
//    private VideoJpaRepository videoJpaRepository;

    @Override
    public APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频列表】，请求参数={0}", request);

        return APIResponse.instance(new ClientVideoListResponse());
    }

    @Override
    @Transactional(readOnly = true)
    public APIResponse<ClientVideoDetailResponse> videoDetail(APIRequest<ClientVideoDetailRequest> request) {
        LogHelper.info(logger, "【客户端】【视频详情】，请求参数={0}", request);
//        ClientVideoDetailRequest bizRequest = request.getBizRequest();
//        // 拿到详情
//        VideoDO resultVideo = videoJpaRepository.getOne(bizRequest.getVideoId());
//        ClientVideoDTO videoDto = null;
//        if (null != resultVideo) {
//            videoDto = CachedBeanCopier.copyConvert(resultVideo, ClientVideoDTO.class);
//            // MongoDB 信息
//            VideoModel videoModel = videoMongoRepositoryImpl.find(resultVideo.getVideoObjectId());
//            if (null != videoModel) {
//                videoDto.setSummary(videoModel.getSummary());
//                videoDto.setDescribe(videoModel.getDescribe());
//                LogHelper.info(logger, "【客户端】【视频详情】，响应值={0}", resultVideo);
//
//            }
//            LogHelper.info(logger, "【客户端】【视频详情】，响应值={0}", resultVideo);
//        }
//        ClientVideoDTO finalVideoDto = videoDto;
//        return APIResponse.instance(new ClientVideoDetailResponse() {{
//            setClientVideo(null != finalVideoDto ? finalVideoDto : new ClientVideoDTO());
//        }});
        return null;
    }
}
