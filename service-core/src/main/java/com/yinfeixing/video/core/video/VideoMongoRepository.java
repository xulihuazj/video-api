package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.BaseMongoRepository;

import java.util.List;

public interface VideoMongoRepository extends BaseMongoRepository<VideoModel> {

    /**
     * 通过视频名称查询视频
     *
     * @param videoName
     * @return
     */
    VideoModel findVideoByVideoName(String videoName);

    /**
     * 模糊查询
     *
     * @param videoName
     * @return
     */
    List<VideoModel> findVideoListByName(String videoName);

    /**
     * 通过ObjectId查询视频
     *
     * @param objectId
     * @return
     */
    VideoModel findVideoByObjectId(String objectId);

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
