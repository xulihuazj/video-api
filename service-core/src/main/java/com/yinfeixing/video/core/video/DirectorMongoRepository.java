package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoDirectorModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface DirectorMongoRepository extends BaseMongoRepository<VideoDirectorModel> {

    /**
     * 查询导演信息
     *
     * @param videoId
     * @param type    类型
     * @return
     */
    List<VideoDirectorModel> findDirectorByVideoId(String videoId, String type);

}