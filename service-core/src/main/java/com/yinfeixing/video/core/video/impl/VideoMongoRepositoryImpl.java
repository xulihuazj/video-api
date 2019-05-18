package com.yinfeixing.video.core.video.impl;

import com.mongodb.client.result.UpdateResult;
import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.MovieModel;
import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.BaseMongoRepositoryImpl;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoMongoRepositoryImpl extends BaseMongoRepositoryImpl<VideoModel> implements VideoMongoRepository {

    @Override
    protected Class<VideoModel> getEntityClass() {
        return VideoModel.class;
    }

    @Override
    public List<MovieModel> findMovieByMovieName(String movieName) {
        Query query = new Query(Criteria.where("movie_name").is(movieName));
        List<MovieModel> model = mongoTemplate.find(query, MovieModel.class);
        return model;
    }

    @Override
    public VideoModel findVideoByVideoName(String videoName) {
        Query query = new Query(Criteria.where("video_name").is(videoName));
        VideoModel model = mongoTemplate.findOne(query, VideoModel.class);
        return model;
    }

    @Override
    public List<VideoModel> findVideoListByName(String videoName) {
        Query query = new Query(Criteria.where("video_name").is(videoName));
        List<VideoModel> modelList = mongoTemplate.find(query, VideoModel.class);
        return modelList;
    }

    @Override
    public VideoModel findVideoByObjectId(String objectId) {
        Query query = new Query(Criteria.where("_id").is(objectId));
        VideoModel model = mongoTemplate.findOne(query, VideoModel.class);
        return model;
    }

    @Override
    public int updateVideo(VideoModel video) {
        Query query = new Query(Criteria.where("id").is(video.getVideoId()));
        Update update = new Update().set("video_name", video.getVideoName()).set("describe", video.getDescribe());
        // 更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, VideoModel.class);
        // 更新查询返回的结果集的所有
//        mongoTemplate.updateMulti(query, update, User.class);
        return null != result ? 1 : 0;
    }

    @Override
    public void deleteVideoById(Long videoId) {
        Query query = new Query(Criteria.where("video_id").is(videoId));
        mongoTemplate.remove(query, VideoModel.class);
    }

    @Override
    public List<VideoModel> searchVideo(List<String> videoObjectIdList, String searchContent, PageModel pageModel) {
        return null;
    }


}
