package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoDO;

@VIDEODB
public interface VideoDOMapper {
    int deleteByPrimaryKey(Long videoId);

    int insert(VideoDO record);

    int insertSelective(VideoDO record);

    VideoDO selectByPrimaryKey(Long videoId);

    int updateByPrimaryKeySelective(VideoDO record);

    int updateByPrimaryKey(VideoDO record);
}