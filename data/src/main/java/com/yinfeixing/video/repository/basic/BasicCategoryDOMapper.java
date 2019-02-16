package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.BasicCategoryDO;

@VIDEODB
public interface BasicCategoryDOMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BasicCategoryDO record);

    int insertSelective(BasicCategoryDO record);

    BasicCategoryDO selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BasicCategoryDO record);

    int updateByPrimaryKey(BasicCategoryDO record);
}