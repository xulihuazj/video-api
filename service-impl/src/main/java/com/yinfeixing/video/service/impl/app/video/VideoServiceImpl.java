package com.yinfeixing.video.service.impl.app.video;

import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.utils.convert.CachedBeanCopier;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.core.video.PerformMongoRepository;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import com.yinfeixing.video.dataobject.video.VideoDO;
import com.yinfeixing.video.dto.app.client.ClientVideoDTO;
import com.yinfeixing.video.repository.video.VideoDOMapper;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoDetailRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoDetailResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.app.video.VideoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VideoServiceImpl implements VideoService {

    private Logger logger = LogManager.getLogger(VideoServiceImpl.class);

//    @Resource
    @Autowired
    @Qualifier(value = "videoMongoRepositoryImpl")
    private VideoMongoRepository videoMongoRepositoryImpl;
    @Resource
    private PerformMongoRepository performMongoRepositoryImpl;
    //    @Resource
//    private VideoJpaRepository videoJpaRepository;
    @Resource
    private VideoDOMapper videoDOMapper;

    @Override
    public APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频列表】，请求参数={0}", request);
        ClientVideoListRequest bizRequest = request.getBizRequest();
        List<VideoModel> videoModelList = videoMongoRepositoryImpl.findAll();
        LogHelper.info(logger, "【客户端】【视频列表】，videoModelList={0}", videoModelList);


        return APIResponse.instance(new ClientVideoListResponse());
    }

    @Override
    @Transactional(readOnly = true)
    public APIResponse<ClientVideoDetailResponse> videoDetail(APIRequest<ClientVideoDetailRequest> request) {
        LogHelper.info(logger, "【客户端】【视频详情】，请求参数={0}", request);
        ClientVideoDetailRequest bizRequest = request.getBizRequest();
        // 拿到详情
        VideoDO resultVideo = videoDOMapper.selectByPrimaryKey(bizRequest.getVideoId());
        ClientVideoDTO videoDto = null;
        if (null != resultVideo) {
            videoDto = CachedBeanCopier.copyConvert(resultVideo, ClientVideoDTO.class);
            // MongoDB 信息
            VideoModel videoModel = videoMongoRepositoryImpl.findVideoByVideoName(resultVideo.getVideoName());
//            this.videoModel = videoMongoRepositoryImpl.find(resultVideo.getVideoObjectId());
            if (null != videoModel) {
                LogHelper.info(logger, "【客户端】【视频详情】，MongoDB响应值={0}", videoModel);
                videoDto.setSummary(videoModel.getSummary());
                videoDto.setDescribe(videoModel.getDescribe());
                LogHelper.info(logger, "【客户端】【视频详情】，响应值={0}", resultVideo);
            }
            List<VideoPerformerModel> performerModelList = performMongoRepositoryImpl.findPerformerByVideoId(resultVideo.getVideoId());
            if (CollectionUtils.isNotEmpty(performerModelList)) {
                List<String> performerList = performerModelList.stream().map(VideoPerformerModel::getPerformerName).collect(toList());
                videoDto.setVideoPerformerList(performerList);
            }
            LogHelper.info(logger, "【客户端】【视频详情】，响应值={0}", resultVideo);
        }
        ClientVideoDTO finalVideoDto = videoDto;
        return APIResponse.instance(new ClientVideoDetailResponse() {{
            setClientVideo(null != finalVideoDto ? finalVideoDto : new ClientVideoDTO());
        }});
    }

    @Override
    @Transactional(readOnly = true)
    public APIResponse<ClientVideoDetailResponse> videoDownloadIncrease(APIRequest<ClientVideoDetailRequest> request) {
        LogHelper.info(logger, "【客户端】【视频下载计数】，请求参数={0}", request);


        ClientVideoDTO finalVideoDto = null;
        return APIResponse.instance(new ClientVideoDetailResponse() {{
            setClientVideo(null != finalVideoDto ? finalVideoDto : new ClientVideoDTO());
        }});
    }


}
