package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoTypeDO;

@VIDEODB
public interface VideoTypeDOMapper {
    int deleteByPrimaryKey(Long videoTypeId);

    int insert(VideoTypeDO record);

    int insertSelective(VideoTypeDO record);

    VideoTypeDO selectByPrimaryKey(Long videoTypeId);

    int updateByPrimaryKeySelective(VideoTypeDO record);

    int updateByPrimaryKey(VideoTypeDO record);
}