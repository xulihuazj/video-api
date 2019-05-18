package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.MovieModel;
import com.yinfeixing.video.core.BaseMongoRepository;

public interface MovieMongoRepository extends BaseMongoRepository<MovieModel> {

    /**
     * 通过名称查询电影
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @param searchContent
     * @return
     */
    PageModel<MovieModel> findMovieBySearchForPage(int pageNo, int pageSize, String searchContent);
}
