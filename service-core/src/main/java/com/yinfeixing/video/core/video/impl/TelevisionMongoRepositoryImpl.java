package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.MovieModel;
import com.yinfeiixng.video.model.mongo.TelevisionModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.TelevisionMongoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TelevisionMongoRepositoryImpl extends BaseMongoRepositoryImpl<TelevisionModel> implements TelevisionMongoRepository {

    @Override
    protected Class<TelevisionModel> getEntityClass() {
        return TelevisionModel.class;
    }

    @Override
    public PageModel<TelevisionModel> findTelevisionBySearchForPage(int pageNo, int pageSize, String searchContent) {
        Map<String, Object> map = StringUtils.isNotBlank(searchContent) ? new HashMap<String, Object>() {{
            put("television_name", searchContent);
        }} : null;
        return super.pageByProps(pageNo, pageSize, map);
    }

    @Override
    public List<TelevisionModel> findTelevision2Hot() {
        return super.findByProp("hot_degree", "HOT");
    }

}
