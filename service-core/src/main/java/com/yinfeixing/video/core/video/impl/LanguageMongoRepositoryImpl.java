package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoLanguageModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.LanguageMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoLanguageModel> implements LanguageMongoRepository {

    @Override
    protected Class<VideoLanguageModel> getEntityClass() {
        return VideoLanguageModel.class;
    }

    @Override
    public List<VideoLanguageModel> findLanguageByVideoId(String videoId, String type) {
        Query query = new Query(Criteria.where("video_id").is(videoId));
        query.addCriteria(Criteria.where("video_type").is(type));
        List<VideoLanguageModel> modelList = mongoTemplate.find(query, VideoLanguageModel.class);
        return modelList;
    }


}