package com.yinfeixing.video.core.video.impl;

import com.mongodb.client.result.UpdateResult;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.VideoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class VideoRepositoryImpl extends BaseMongoRepositoryImpl<VideoModel> implements VideoRepository {

    @Override
    protected Class<VideoModel> getEntityClass() {
        return VideoModel.class;
    }

    @Override
    public VideoModel findVideoByVideoName(String videoName) {
        Query query = new Query(Criteria.where("videoName").is(videoName));
        VideoModel model = mongoTemplate.findOne(query, VideoModel.class);
        return model;
    }

    @Override
    public int updateVideo(VideoModel video) {
        Query query = new Query(Criteria.where("id").is(video.getVideoId()));
        Update update = new Update().set("videoName", video.getVideoName()).set("describe", video.getDescribe());
        // 更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, VideoModel.class);
        // 更新查询返回的结果集的所有
//        mongoTemplate.updateMulti(query, update, User.class);
        return null != result ? 1 : 0;
    }

    @Override
    public void deleteVideoById(Long videoId) {
        Query query = new Query(Criteria.where("videoId").is(videoId));
        mongoTemplate.remove(query, VideoModel.class);
    }


}
