package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface PerformMongoRepository extends BaseMongoRepository<VideoPerformerModel> {

    /**
     * 查询演员信息
     *
     * @param videoId 主键
     * @param type    类型：MOVIE：电影、TELEVISION：电视剧、动漫：ANIMATION、综艺：VARIETY
     * @return
     */
    List<VideoPerformerModel> findPerformerByVideoId(String videoId, String type);

}
