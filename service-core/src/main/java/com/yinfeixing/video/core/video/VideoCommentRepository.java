package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoCommentModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface VideoCommentRepository extends BaseMongoRepository<VideoCommentModel> {

    /**
     * 查询评论信息
     *
     * @param videoId
     * @param type    类型
     * @return
     */
    List<VideoCommentModel> findCommentByVideoId(String videoId, String type);

}