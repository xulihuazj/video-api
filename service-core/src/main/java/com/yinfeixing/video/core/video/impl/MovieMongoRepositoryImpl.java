package com.yinfeixing.video.core.video.impl;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.MovieModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.MovieMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieMongoRepositoryImpl extends BaseMongoRepositoryImpl<MovieModel> implements MovieMongoRepository {

    @Override
    protected Class<MovieModel> getEntityClass() {
        return MovieModel.class;
    }

    @Override
    public PageModel<MovieModel> findMovieBySearchForPage(int pageNo, int pageSize, String searchContent) {
        return super.pageByProps(pageNo, pageSize, new HashMap<String, Object>() {{
            put("movie_name", searchContent);
//            put("alias_name", searchContent);
        }});
    }

    @Override
    public List<MovieModel> findMovie2Hot() {
        return super.findByProp("hot_degree", "HOT");
    }

}

