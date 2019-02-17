package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface PerformMongoRepository extends BaseMongoRepository<VideoPerformerModel> {


    List<VideoPerformerModel> findPerformerByVideoId(Long videoId);

}
