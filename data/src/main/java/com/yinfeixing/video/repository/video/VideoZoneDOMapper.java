package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoZoneDO;

@VIDEODB
public interface VideoZoneDOMapper {
    int deleteByPrimaryKey(Long videoZoneId);

    int insert(VideoZoneDO record);

    int insertSelective(VideoZoneDO record);

    VideoZoneDO selectByPrimaryKey(Long videoZoneId);

    int updateByPrimaryKeySelective(VideoZoneDO record);

    int updateByPrimaryKey(VideoZoneDO record);
}