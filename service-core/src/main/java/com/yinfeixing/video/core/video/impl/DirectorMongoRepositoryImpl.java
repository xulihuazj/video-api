package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoDirectorModel;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.DirectorMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DirectorMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoDirectorModel> implements DirectorMongoRepository {

    @Override
    protected Class<VideoDirectorModel> getEntityClass() {
        return VideoDirectorModel.class;
    }

    @Override
    public List<VideoDirectorModel> findDirectorByVideoId(Long videoId) {
        Query query = new Query(Criteria.where("videoId").is(videoId));
        List<VideoDirectorModel> modelList = mongoTemplate.find(query, VideoDirectorModel.class);
        return modelList;
    }


}
