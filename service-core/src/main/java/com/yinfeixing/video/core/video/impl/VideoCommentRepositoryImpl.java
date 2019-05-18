package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoCommentModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.VideoCommentRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoCommentRepositoryImpl extends BaseMongoRepositoryImpl<VideoCommentModel> implements VideoCommentRepository {

    @Override
    protected Class<VideoCommentModel> getEntityClass() {
        return VideoCommentModel.class;
    }

    @Override
    public List<VideoCommentModel> findCommentByVideoId(String videoId, String type) {
        Query query = new Query(Criteria.where("video_id").is(videoId));
        query.addCriteria(Criteria.where("video_type").is(type));
        List<VideoCommentModel> modelList = mongoTemplate.find(query, VideoCommentModel.class);
        return modelList;
    }
}
