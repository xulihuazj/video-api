package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.BasicZoneDO;

@VIDEODB
public interface BasicZoneDOMapper {
    int deleteByPrimaryKey(Integer zoneId);

    int insert(BasicZoneDO record);

    int insertSelective(BasicZoneDO record);

    BasicZoneDO selectByPrimaryKey(Integer zoneId);

    int updateByPrimaryKeySelective(BasicZoneDO record);

    int updateByPrimaryKey(BasicZoneDO record);
}