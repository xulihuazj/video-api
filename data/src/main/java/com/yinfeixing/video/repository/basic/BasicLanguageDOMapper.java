package com.yinfeixing.video.repository.basic;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.basic.BasicLanguageDO;

@VIDEODB
public interface BasicLanguageDOMapper {
    int deleteByPrimaryKey(Integer languageId);

    int insert(BasicLanguageDO record);

    int insertSelective(BasicLanguageDO record);

    BasicLanguageDO selectByPrimaryKey(Integer languageId);

    int updateByPrimaryKeySelective(BasicLanguageDO record);

    int updateByPrimaryKey(BasicLanguageDO record);
}