package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.BasicYearDO;

@VIDEODB
public interface BasicYearDOMapper {
    int deleteByPrimaryKey(Integer yearId);

    int insert(BasicYearDO record);

    int insertSelective(BasicYearDO record);

    BasicYearDO selectByPrimaryKey(Integer yearId);

    int updateByPrimaryKeySelective(BasicYearDO record);

    int updateByPrimaryKey(BasicYearDO record);
}