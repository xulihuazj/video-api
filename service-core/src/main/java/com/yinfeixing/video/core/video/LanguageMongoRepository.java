package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoLanguageModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface LanguageMongoRepository  extends BaseMongoRepository<VideoLanguageModel> {

    /**
     * 查询导演信息
     *
     * @param videoId
     * @param type    类型
     * @return
     */
    List<VideoLanguageModel> findLanguageByVideoId(String videoId, String type);

}
