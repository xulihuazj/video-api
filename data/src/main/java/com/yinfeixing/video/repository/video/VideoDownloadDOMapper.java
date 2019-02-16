package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoDownloadDO;

@VIDEODB
public interface VideoDownloadDOMapper {
    int deleteByPrimaryKey(Long videoAddressId);

    int insert(VideoDownloadDO record);

    int insertSelective(VideoDownloadDO record);

    VideoDownloadDO selectByPrimaryKey(Long videoAddressId);

    int updateByPrimaryKeySelective(VideoDownloadDO record);

    int updateByPrimaryKey(VideoDownloadDO record);
}