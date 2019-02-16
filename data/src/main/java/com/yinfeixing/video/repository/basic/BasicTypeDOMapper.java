package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.BasicTypeDO;

@VIDEODB
public interface BasicTypeDOMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(BasicTypeDO record);

    int insertSelective(BasicTypeDO record);

    BasicTypeDO selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(BasicTypeDO record);

    int updateByPrimaryKey(BasicTypeDO record);
}