package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoReleaseDO;

@VIDEODB
public interface VideoReleaseDOMapper {
    int deleteByPrimaryKey(Integer videoReleaseId);

    int insert(VideoReleaseDO record);

    int insertSelective(VideoReleaseDO record);

    VideoReleaseDO selectByPrimaryKey(Integer videoReleaseId);

    int updateByPrimaryKeySelective(VideoReleaseDO record);

    int updateByPrimaryKey(VideoReleaseDO record);
}