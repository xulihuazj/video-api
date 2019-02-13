package com.yinfeixing.video.repository.client;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.client.ClientUserLoginLogDO;
import org.apache.ibatis.annotations.Param;

@VIDEODB
public interface ClientUserLoginLogDOMapper {
    int insert(ClientUserLoginLogDO record);

    int insertSelective(ClientUserLoginLogDO record);

    /**
      * @Author: xulh
      * @Description:  通过用户token 和登录agent信息获取登录信息
      * @Date: 15:17 2018-06-04
      * @return
      */
    ClientUserLoginLogDO selectByTokenAndAgent(@Param(value = "loginToken")String loginToken,
                                               @Param(value = "userAgent")String userAgent,@Param(value = "loginStatus") String loginStatus);
    /**
     *  更新用户登录信息
     */
    int updateByTokenAndAgentSelective(ClientUserLoginLogDO record);
    
    ClientUserLoginLogDO lastLoginInfo(@Param("userId") Long userId);

}