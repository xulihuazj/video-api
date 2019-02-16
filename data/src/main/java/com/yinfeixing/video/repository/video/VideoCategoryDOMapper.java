package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoCategoryDO;

@VIDEODB
public interface VideoCategoryDOMapper {
    int deleteByPrimaryKey(Long videoCategoryId);

    int insert(VideoCategoryDO record);

    int insertSelective(VideoCategoryDO record);

    VideoCategoryDO selectByPrimaryKey(Long videoCategoryId);

    int updateByPrimaryKeySelective(VideoCategoryDO record);

    int updateByPrimaryKey(VideoCategoryDO record);
}