package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoImageModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.ImageMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ImageMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoImageModel> implements ImageMongoRepository {

    @Override
    protected Class<VideoImageModel> getEntityClass() {
        return VideoImageModel.class;
    }
}
