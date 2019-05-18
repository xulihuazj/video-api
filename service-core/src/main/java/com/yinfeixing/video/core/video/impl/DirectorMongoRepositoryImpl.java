package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoDirectorModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.DirectorMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoDirectorModel> implements DirectorMongoRepository {

    @Override
    protected Class<VideoDirectorModel> getEntityClass() {
        return VideoDirectorModel.class;
    }

    @Override
    public List<VideoDirectorModel> findDirectorByVideoId(String videoId, String type) {
        Query query = new Query(Criteria.where("video_id").is(videoId));
        query.addCriteria(Criteria.where("video_type").is(type));
        List<VideoDirectorModel> modelList = mongoTemplate.find(query, VideoDirectorModel.class);
        return modelList;
    }


}
