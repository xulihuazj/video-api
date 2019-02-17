package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.VideoDirectorDO;

@VIDEODB
public interface VideoDirectorDOMapper {
    int deleteByPrimaryKey(Integer videoDirectorId);

    int insert(VideoDirectorDO record);

    int insertSelective(VideoDirectorDO record);

    VideoDirectorDO selectByPrimaryKey(Integer videoDirectorId);

    int updateByPrimaryKeySelective(VideoDirectorDO record);

    int updateByPrimaryKey(VideoDirectorDO record);
}