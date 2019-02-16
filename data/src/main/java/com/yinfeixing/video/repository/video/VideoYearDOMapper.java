package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoYearDO;

@VIDEODB
public interface VideoYearDOMapper {
    int deleteByPrimaryKey(Long videoYearId);

    int insert(VideoYearDO record);

    int insertSelective(VideoYearDO record);

    VideoYearDO selectByPrimaryKey(Long videoYearId);

    int updateByPrimaryKeySelective(VideoYearDO record);

    int updateByPrimaryKey(VideoYearDO record);
}