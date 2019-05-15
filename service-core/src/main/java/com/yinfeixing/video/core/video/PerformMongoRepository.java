package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface PerformMongoRepository extends BaseMongoRepository<VideoPerformerModel> {

    /**
     * 查询演员信息
     *
     * @param videoId
     * @return
     */
    List<VideoPerformerModel> findPerformerByVideoId(Long videoId);

}
