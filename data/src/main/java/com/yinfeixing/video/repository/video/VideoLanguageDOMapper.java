package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoLanguageDO;

@VIDEODB
public interface VideoLanguageDOMapper {
    int deleteByPrimaryKey(Long videLanguageId);

    int insert(VideoLanguageDO record);

    int insertSelective(VideoLanguageDO record);

    VideoLanguageDO selectByPrimaryKey(Long videLanguageId);

    int updateByPrimaryKeySelective(VideoLanguageDO record);

    int updateByPrimaryKey(VideoLanguageDO record);
}