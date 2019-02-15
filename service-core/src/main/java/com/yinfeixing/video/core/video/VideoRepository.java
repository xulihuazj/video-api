package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.BaseMongoRepository;

public interface VideoRepository extends BaseMongoRepository<VideoModel> {

    /**
     * 通过视频名称查询视频
     *
     * @param videoName
     * @return
     */
    VideoModel findVideoByVideoName(String videoName);

    /**
     * 更新视频信息
     *
     * @param video
     * @return
     */
    int updateVideo(VideoModel video);

    /**
     * 删除视频信息
     *
     * @param videoId
     */
    void deleteVideoById(Long videoId);


}
