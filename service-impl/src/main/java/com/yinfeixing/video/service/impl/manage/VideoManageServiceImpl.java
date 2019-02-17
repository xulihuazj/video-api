package com.yinfeixing.video.service.impl.manage;

import com.yinfeiixng.video.model.mongo.VideoImageModel;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.core.BaseMongoRepository;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.ClientVideoListRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.service.impl.app.video.VideoServiceImpl;
import com.yinfeixing.video.service.manage.VideoManageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoManageServiceImpl implements VideoManageService {

    private Logger logger = LogManager.getLogger(VideoManageServiceImpl.class);

    @Resource
    private VideoMongoRepository videoMongoRepositoryImpl;
    @Resource
    private BaseMongoRepository baseMongoRepositoryImpl;

    @Override
    public APIResponse<ClientVideoListResponse> videoUpdate(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频更新】，请求参数={0}", request);

        VideoModel video = videoMongoRepositoryImpl.findVideoByVideoName("流浪地球");
        LogHelper.info(logger, "【客户端】【视频更新】，model={0}", video);
        VideoImageModel imageModel1 = new VideoImageModel() {{
            setVideoId(2L);
            setVideoImageId(1L);
            setVideoImageUrl("http://img1.52mamahome.com//hotel/8577905884221666.jpg");
            setVideoImageStatus("EFFECTIVE");
        }};
        VideoImageModel imageModel2 = new VideoImageModel() {{
            setVideoId(2L);
            setVideoImageId(2L);
            setVideoImageUrl("http://img1.52mamahome.com//hotel/620811780848501.jpg");
            setVideoImageStatus("EFFECTIVE");
        }};
        baseMongoRepositoryImpl.save(imageModel1);
        baseMongoRepositoryImpl.save(imageModel2);
        List<VideoImageModel> videoImageModelList = Arrays.asList(
                imageModel1, imageModel2
        );
        VideoPerformerModel performerModel = new VideoPerformerModel() {{
            setVideoId(2L);
            setPerformerName("怀亚特·罗素");
        }};
        VideoPerformerModel performerMode2 = new VideoPerformerModel() {{
            setVideoId(2L);
            setPerformerName("皮鲁·埃斯贝克");
        }};
        VideoPerformerModel performerMode3 = new VideoPerformerModel() {{
            setVideoId(2L);
            setPerformerName("约翰·艾德坡");
        }};
        VideoPerformerModel performerMode4 = new VideoPerformerModel() {{
            setVideoId(2L);
            setPerformerName("马蒂尔德·奥利维耶");
        }};
        List<VideoPerformerModel> videoPerformerModelList = Arrays.asList(
                performerModel, performerMode2, performerMode3, performerMode4
        );
        VideoModel model = new VideoModel();
        model.setVideoId(2L);
        model.setVideoName("流浪地球2222");
        model.setSummary("出乎意料的B級片！狂暴B级血浆+年度A级体验 ");
        model.setDescribe("被称为“中国第一部真正的科幻电影”，《流浪地球》的成功被赋予了“划时代”的意义，" +
                "也吸引了许多曾经对“科幻片”这个标签不感兴趣的观众走进了电影院。" +
                "《人民日报》点评该片称：“文学艺术离不开生长的土壤，科幻电影也不例外。" +
                "科技的迅速发展，为科幻文学和科幻电影发展提供了沃土。" +
                "影片的成功反映的是电影工业乃至国家的综合实力。”");
        model.setVideoImageList(videoImageModelList);
        model.setVideoPerformerList(videoPerformerModelList);
        videoMongoRepositoryImpl.save(model);
        return APIResponse.instance(new ClientVideoListResponse());
    }
}
