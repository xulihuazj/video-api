package com.yinfeixing.video.service.impl.manage;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.VideoAliasModel;
import com.yinfeiixng.video.model.mongo.VideoImageModel;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.core.BaseMongoRepository;
import com.yinfeixing.video.core.video.ImageMongoRepository;
import com.yinfeixing.video.core.video.PerformMongoRepository;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.manage.VideoManageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoManageServiceImpl implements VideoManageService {

    private static final Logger logger = LogManager.getLogger(VideoManageServiceImpl.class);

    //    @Resource
    @Autowired
    @Qualifier(value = "videoMongoRepositoryImpl")
    private VideoMongoRepository videoMongoRepositoryImpl;
    @Autowired
    @Qualifier(value = "imageMongoRepositoryImpl")
    private ImageMongoRepository imageMongoRepositoryImpl;
    @Autowired
    @Qualifier(value = "performMongoRepositoryImpl")
    private PerformMongoRepository performMongoRepositoryImpl;
    //    @Resource
//    @Autowired
//    @Qualifier(value = "baseMongoRepositoryImpl")
//    private BaseMongoRepository baseMongoRepositoryImpl;

    @Override
    public APIResponse<ClientVideoListResponse> videoUpdate(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频更新】，请求参数={0}", request);

        VideoModel video = videoMongoRepositoryImpl.findVideoByVideoName("流浪地球");
        LogHelper.info(logger, "【客户端】【视频更新】，model={0}", video);
        VideoImageModel imageModel1 = new VideoImageModel() {{
            setVideoId(3L);
            setVideoImageId(3L);
            setVideoImageUrl("http://img1.52mamahome.com//hotel/8577905884221666.jpg");
            setVideoImageStatus("EFFECTIVE");
        }};
        VideoImageModel imageModel2 = new VideoImageModel() {{
            setVideoId(3L);
            setVideoImageId(4L);
            setVideoImageUrl("http://img1.52mamahome.com//hotel/620811780848501.jpg");
            setVideoImageStatus("EFFECTIVE");
        }};
        imageMongoRepositoryImpl.save(imageModel1);
        imageMongoRepositoryImpl.save(imageModel2);
        List<VideoImageModel> videoImageModelList = Arrays.asList(
                imageModel1, imageModel2
        );
        VideoPerformerModel performerModel = new VideoPerformerModel() {{
            setVideoId(3L);
            setPerformerName("威廉·达福");
        }};
        VideoPerformerModel performerMode2 = new VideoPerformerModel() {{
            setVideoId(3L);
            setPerformerName("帕特里克·威尔森");
        }};
        VideoPerformerModel performerMode3 = new VideoPerformerModel() {{
            setVideoId(3L);
            setPerformerName("杰森·莫玛");
        }};
        VideoPerformerModel performerMode4 = new VideoPerformerModel() {{
            setVideoId(3L);
            setPerformerName("艾梅柏·希尔德");
        }};
        performMongoRepositoryImpl.save(performerModel);
        performMongoRepositoryImpl.save(performerMode2);
        performMongoRepositoryImpl.save(performerMode3);
        performMongoRepositoryImpl.save(performerMode4);
        List<VideoPerformerModel> videoPerformerModelList = Arrays.asList(
                performerModel, performerMode2, performerMode3, performerMode4
        );
        VideoAliasModel videoAliasModel = new VideoAliasModel() {{
            setVideoId(3L);
            setAliasName("水行侠(港/台)");
            setAliasStatus("EFFECTIVE");
        }};
        VideoAliasModel videoAliasMode2 = new VideoAliasModel() {{
            setVideoId(3L);
            setAliasName("潜水侠");
            setAliasStatus("EFFECTIVE");
        }};
        VideoAliasModel videoAliasMode3 = new VideoAliasModel() {{
            setVideoId(3L);
            setAliasName("Aquaman");
            setAliasStatus("EFFECTIVE");
        }};
//        baseMongoRepositoryImpl.save(videoAliasModel);
//        baseMongoRepositoryImpl.save(videoAliasMode2);
//        baseMongoRepositoryImpl.save(videoAliasMode3);
        List<VideoAliasModel> videoAliasList = Arrays.asList(
                videoAliasModel, videoAliasMode2, videoAliasMode3
        );
        VideoModel model = new VideoModel();
        model.setVideoId(3L);
        model.setVideoName("海王");
        model.setSummary("爆炸和战斗，是一门艺术。暂更新至韩版硬字幕");
        model.setDescribe("华纳兄弟影片公司与导演温子仁联手为您呈现波澜壮阔的动作冒险电影——《海王》！" +
                "横跨七大洋的广阔海底世界徐徐展开，给观众带来震撼十足的视觉奇观。本片由杰森·莫玛领衔主演，" +
                "讲述半人半亚特兰蒂斯血统的亚瑟·库瑞踏上永生难忘的征途——他不但需要直面自己的特殊身世，" +
                "更不得不面对生而为王的考验：自己究竟能否配得上“海王”之名。");
        model.setVideoImageList(videoImageModelList);
        model.setVideoPerformerList(videoPerformerModelList);
        model.setVideoAliasList(videoAliasList);
        videoMongoRepositoryImpl.save(model);
        return APIResponse.instance(new ClientVideoListResponse());
    }

    @Override
    public APIResponse<ClientVideoListResponse> videoSearch(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频更新】，请求参数={0}", request);
        ClientVideoListRequest bizRequest = request.getBizRequest();
        String searchContent = bizRequest.getSearchContent();
        PageModel pageModel = new PageModel(bizRequest.getPageNum(), bizRequest.getPageSize());
        List<String> videoObjectIdList = Arrays.asList("0");

        videoMongoRepositoryImpl.searchVideo(videoObjectIdList, searchContent, pageModel);


        return null;
    }

    @Override
    public APIResponse<ClientVideoListResponse> videoDetail(APIRequest<ClientVideoListRequest> apiRequest) {
        return null;
    }
}
