package com.yinfeixing.video.service.impl.app.video;

import com.yinfeiixng.video.model.PageModel;
import com.yinfeiixng.video.model.mongo.*;
import com.yinfeixing.utils.convert.CachedBeanCopier;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.core.video.*;
import com.yinfeixing.video.dto.app.client.ClientVideoDTO;
import com.yinfeixing.video.dto.video.DicDTO;
import com.yinfeixing.video.dto.video.VideoCommentDTO;
import com.yinfeixing.video.dto.video.VideoDTO;
import com.yinfeixing.video.dto.video.VideoDTOBuilder;
import com.yinfeixing.video.repository.video.VideoDOMapper;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.video.*;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.video.ClientTelevisionListResponse;
import com.yinfeixing.video.response.app.video.ClientVideoDetailResponse;
import com.yinfeixing.video.response.app.video.ClientVideoListResponse;
import com.yinfeixing.video.response.app.video.DicResponse;
import com.yinfeixing.video.service.app.video.VideoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VideoServiceImpl implements VideoService {

    private Logger logger = LogManager.getLogger(VideoServiceImpl.class);

    //    @Resource
    @Autowired
    @Qualifier(value = "videoMongoRepositoryImpl")
    private VideoMongoRepository videoMongoRepositoryImpl;
    @Autowired
    @Qualifier(value = "movieMongoRepositoryImpl")
    private MovieMongoRepository movieMongoRepositoryImpl;
    @Autowired
    private TelevisionMongoRepository televisionMongoRepositoryImpl;
    @Resource
    private PerformMongoRepository performMongoRepositoryImpl;
    @Resource
    private DirectorMongoRepository directorMongoRepositoryImpl;
    @Resource
    private LanguageMongoRepository languageMongoRepositoryImpl;
    @Resource
    private VideoCommentRepository videoCommentRepositoryImpl;
    @Resource
    private MongoTemplate mongoTemplate;
    //    @Resource
//    private DicLanguageRepository dicLanguageRepositoryImpl;
//    @Resource
//    private DicZoneRepository dicZoneRepositoryImpl;
//    @Resource
//    private DicYearRepository dicYearRepositoryImpl;
//    @Resource
//    private DicCategoryRepository dicCategoryRepositoryImpl;
    //    @Resource
//    private VideoJpaRepository videoJpaRepository;
    @Resource
    private VideoDOMapper videoDOMapper;

    @Override
    public APIResponse<ClientVideoListResponse> videoList(APIRequest<ClientVideoListRequest> request) {
        LogHelper.info(logger, "【客户端】【视频列表】，请求参数={0}", request);
        ClientVideoListRequest bizRequest = request.getBizRequest();
        PageModel<MovieModel> moviePageModel = movieMongoRepositoryImpl.findMovieBySearchForPage(bizRequest.getPageNum(), bizRequest.getPageSize(), bizRequest.getSearchContent());
        ClientVideoListResponse bizResponse = new ClientVideoListResponse();
        if (null != moviePageModel) {
            bizResponse.setTotal(moviePageModel.getTotalCount());
            bizResponse.setTotalPage(moviePageModel.getTotalPage());
            List<MovieModel> result = moviePageModel.getResult();
            List<VideoDTO> videoList;
            if (CollectionUtils.isNotEmpty(result)) {
                videoList = new ArrayList<>(result.size());
                for (MovieModel movie : result) {
                    VideoDTO videoDTO = CachedBeanCopier.copyConvert(movie, VideoDTO.class);
                    videoDTO.setVideoObjectId(movie.getId());
                    videoDTO.setVideoName(movie.getMovieName());
                    videoDTO.setVideoLength(movie.getMovieLength());
                    videoDTO.setVideoImage(movie.getMovieImage());
                    videoList.add(videoDTO);
                }
            } else {
                videoList = new ArrayList<>(0);
            }
            bizResponse.setVideoList(videoList);
        }
        LogHelper.info(logger, "【客户端】【视频列表】，videoModelList={0}", bizResponse);
        return APIResponse.instance(bizResponse);
    }

    @Override
    public APIResponse<ClientVideoListResponse> videoRecommendList(APIRequest<ClientVideoRecommendRequest> request) {
        LogHelper.info(logger, "【客户端】【热门电影推荐列表】，请求参数={0}", request);
        ClientVideoRecommendRequest bizRequest = request.getBizRequest();
        ClientVideoListResponse bizResponse = new ClientVideoListResponse();
        List<VideoDTO> videoList = new ArrayList<>();
        switch (bizRequest.getRecommendVideoType()) {
            case "MOVIE":
                List<MovieModel> result = movieMongoRepositoryImpl.findMovie2Hot();
                for (MovieModel movie : result) {
                    VideoDTO videoDTO = new VideoDTO();
                    videoDTO.setVideoObjectId(movie.getId());
                    videoDTO.setVideoName(movie.getMovieName());
                    videoDTO.setVideoImage(movie.getMovieImage());
                    videoDTO.setDescribe(movie.getDescribe());
                    videoList.add(videoDTO);
                }
                bizResponse.setVideoList(videoList);
                break;
            case "TELEVISION":
                List<TelevisionModel> televisionList = televisionMongoRepositoryImpl.findTelevision2Hot();
                for (TelevisionModel television : televisionList) {
                    VideoDTO videoDTO = VideoDTOBuilder.aVideoDTO().videoObjectId(television.getId())
                            .videoName(television.getTelevisionName()).videoImage(television.getMovieImage()).describe(television.getDescribe()).build();
                    videoList.add(videoDTO);
                }
                bizResponse.setVideoList(videoList);
                break;
            case "VARIETY":
                break;
            case "ANIMATION":
                break;
            default:
                break;
        }
        LogHelper.info(logger, "【客户端】【热门电影推荐列表】，videoModelList={0}", bizResponse);
        return APIResponse.instance(bizResponse);
    }

    @Override
    public APIResponse<ClientVideoDetailResponse> videoDetail(APIRequest<ClientVideoDetailRequest> request) {
        LogHelper.info(logger, "【客户端】【视频详情】，请求参数={0}", request);
        ClientVideoDetailRequest bizRequest = request.getBizRequest();
        String videoObjectId = bizRequest.getVideoId();
        MovieModel movieModel = movieMongoRepositoryImpl.find(videoObjectId);
        ClientVideoDTO videoDto = new ClientVideoDTO();
        if (null != movieModel) {
            LogHelper.info(logger, "【客户端】【视频详情】，MongoDB响应值={0}", movieModel);
            videoDto.setVideoName(movieModel.getMovieName());
            videoDto.setYearNum(movieModel.getYearNum());
            videoDto.setVideoResolution(movieModel.getMovieResolution());
            videoDto.setReleaseTime(movieModel.getReleaseTime());
            videoDto.setLanguageName(movieModel.getLanguageName());
            videoDto.setVideoImage(movieModel.getMovieImage());
            videoDto.setZoneName(movieModel.getZoneName());
            videoDto.setSummary(movieModel.getSummary());
            videoDto.setDescribe(movieModel.getDescribe());
            List<VideoPerformerModel> performerModelList = performMongoRepositoryImpl.findPerformerByVideoId(movieModel.getId(), "MOVIE");
            // 演员
            if (CollectionUtils.isNotEmpty(performerModelList)) {
                List<String> performerList = performerModelList.stream().map(VideoPerformerModel::getPerformerName).collect(toList());
                videoDto.setVideoPerformerList(performerList);
            }
            List<VideoDirectorModel> videoDirectorList = directorMongoRepositoryImpl.findDirectorByVideoId(movieModel.getId(), "MOVIE");
            // 导演
            if (CollectionUtils.isNotEmpty(videoDirectorList)) {
                List<String> directorList = videoDirectorList.stream().map(VideoDirectorModel::getDirectorName).collect(toList());
                videoDto.setVideoDirectorList(directorList);
            }
            List<VideoLanguageModel> videoLanguageList = languageMongoRepositoryImpl.findLanguageByVideoId(movieModel.getId(), "MOVIE");
            // 语言
            if (CollectionUtils.isNotEmpty(videoLanguageList)) {
                List<String> languageList = videoLanguageList.stream().map(VideoLanguageModel::getLanguageName).collect(toList());
                videoDto.setVideoLanguageList(languageList);
            }
            List<VideoCommentModel> videoCommentList = videoCommentRepositoryImpl.findCommentByVideoId(movieModel.getId(), "MOVIE");
            // 评论
            if (CollectionUtils.isNotEmpty(videoCommentList)) {
                List<VideoCommentDTO> commentList = CachedBeanCopier.copyConvertList(videoCommentList, VideoCommentDTO.class);
                videoDto.setVideoCommentList(commentList);
            }
            LogHelper.info(logger, "【客户端】【视频详情】，响应值={0}", videoDto);

        }
        ClientVideoDTO finalVideoDto = videoDto;
        return APIResponse.instance(new ClientVideoDetailResponse() {{
            setClientVideo(null != finalVideoDto ? finalVideoDto : new ClientVideoDTO());
        }});
    }

    @Override
    @Transactional(readOnly = true)
    public APIResponse<ClientVideoDetailResponse> videoDownloadIncrease(APIRequest<ClientVideoDetailRequest> request) {
        LogHelper.info(logger, "【客户端】【视频下载计数】，请求参数={0}", request);


        ClientVideoDTO finalVideoDto = null;
        return APIResponse.instance(new ClientVideoDetailResponse() {{
            setClientVideo(null != finalVideoDto ? finalVideoDto : new ClientVideoDTO());
        }});
    }


    @Override
    public APIResponse<DicResponse> baseInfoSearch(APIRequest<DicRequest> apiRequest) {
        LogHelper.info(logger, "【客户端】【基础字典查询】，请求参数={0}", apiRequest);
        DicRequest bizRequest = apiRequest.getBizRequest();
        List<DicDTO> dicList;
        switch (bizRequest.getType()) {
            case "ZONE":
                List<DicZoneModel> zoneList = mongoTemplate.findAll(DicZoneModel.class);
                dicList = zoneList.stream().map(zone -> new DicDTO() {{
                    setDicCode(zone.getId());
                    setDicValue(zone.getZone());
                }}).collect(toList());
                break;
            case "YEAR":
                List<DicYearModel> yearList = mongoTemplate.findAll(DicYearModel.class);
                dicList = yearList.stream().map(zone -> new DicDTO() {{
                    setDicCode(zone.getId());
                    setDicValue(zone.getYear());
                }}).collect(toList());
                break;
            case "LANGUAGE":
                List<DicLanguageModel> languageList = mongoTemplate.findAll(DicLanguageModel.class);
                dicList = languageList.stream().map(zone -> new DicDTO() {{
                    setDicCode(zone.getId());
                    setDicValue(zone.getLanguage());
                }}).collect(toList());
                break;
            case "CATEGORY":
                List<DicCategoryModel> categoryList = mongoTemplate.findAll(DicCategoryModel.class);
                dicList = categoryList.stream().map(zone -> new DicDTO() {{
                    setDicCode(zone.getId());
                    setDicValue(zone.getCategory());
                }}).collect(toList());
                break;
            default:
                dicList = new ArrayList<>();
                break;
        }
        DicResponse dicResponse = new DicResponse();
        dicResponse.setDicList(CollectionUtils.isNotEmpty(dicList) ? dicList : new ArrayList<>());
        LogHelper.info(logger, "【客户端】【基础字典查询】，响应内容={0}", dicResponse);
        return APIResponse.instance(dicResponse);
    }

    @Override
    public APIResponse<ClientTelevisionListResponse> televisionList(APIRequest<ClientTelevisionListRequest> apiRequest) {
        ClientTelevisionListRequest bizRequest = apiRequest.getBizRequest();
        Integer pageNum = null != bizRequest.getPageNum() ? bizRequest.getPageNum() : 1;
        Integer pageSize = null != bizRequest.getPageSize() ? bizRequest.getPageSize() : 30;
        PageModel<TelevisionModel> pageModel = televisionMongoRepositoryImpl.findTelevisionBySearchForPage(pageNum, pageSize, bizRequest.getSearchContent());
        ClientTelevisionListResponse bizResponse = new ClientTelevisionListResponse();
        if (null != pageModel) {
            bizResponse.setTotal(pageModel.getTotalCount());
            bizResponse.setTotalPage(pageModel.getTotalPage());
            List<TelevisionModel> result = pageModel.getResult();
            List<VideoDTO> videoList = new ArrayList<>();
            for (TelevisionModel television : result) {
                VideoDTO videoDTO = CachedBeanCopier.copyConvert(television, VideoDTO.class);
                videoDTO.setVideoObjectId(television.getId());
                videoDTO.setVideoName(television.getTelevisionName());
                videoDTO.setVideoLength(television.getMovieLength());
                videoDTO.setVideoImage(television.getMovieImage());
                videoList.add(videoDTO);
            }
            bizResponse.setVideoList(videoList);
        }
        return APIResponse.instance(bizResponse);
    }


}
