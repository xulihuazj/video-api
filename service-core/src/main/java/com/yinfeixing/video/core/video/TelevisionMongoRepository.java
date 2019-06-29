package com.yinfeixing.video.core.video;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.TelevisionModel;

import java.util.List;

public interface TelevisionMongoRepository {

    /**
     * 通过名称查询电视剧
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @param searchContent
     * @return
     */
    PageModel<TelevisionModel> findTelevisionBySearchForPage(int pageNo, int pageSize, String searchContent);


    /**
     * 热门电视剧
     *
     * @return
     */
    List<TelevisionModel> findTelevision2Hot();

}
