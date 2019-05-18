package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoPerformerModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.PerformMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PerformMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoPerformerModel> implements PerformMongoRepository {

    @Override
    protected Class<VideoPerformerModel> getEntityClass() {
        return VideoPerformerModel.class;
    }

    @Override
    public List<VideoPerformerModel> findPerformerByVideoId(String videoId, String type) {
        Query query = new Query(Criteria.where("video_id").is(videoId));
        query.addCriteria(Criteria.where("video_type").is(type));
        List<VideoPerformerModel> modelList = mongoTemplate.find(query, VideoPerformerModel.class);
        return modelList;
    }
}
