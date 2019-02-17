package com.yinfeixing.video.repository.video;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.video.VideoDO;
import org.apache.ibatis.annotations.Select;

@VIDEODB
public interface VideoDOMapper {
    int deleteByPrimaryKey(Long videoId);

    int insert(VideoDO record);

    int insertSelective(VideoDO record);

    @Select("select * from video_info where video_id = #{videoId}")
    VideoDO selectByPrimaryKey2(Long videoId);

    VideoDO selectByPrimaryKey(Long videoId);

    int updateByPrimaryKeySelective(VideoDO record);

    int updateByPrimaryKey(VideoDO record);
}