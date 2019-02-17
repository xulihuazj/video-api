package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.mongo.VideoAliasModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.AliasMongoRespository;

public class AliasMongoRespositoryImpl extends BaseMongoRepositoryImpl<VideoAliasModel> implements AliasMongoRespository {

    @Override
    protected Class<VideoAliasModel> getEntityClass() {
        return VideoAliasModel.class;
    }
}
