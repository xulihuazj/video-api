package com.yinfeixing.video.repository.client;

import com.cf.pms.dataconfig.annotation.PMSDB;
import com.yinfeixing.video.dataobject.client.ClientUserLoginLogDO;
import org.apache.ibatis.annotations.Param;

@PMSDB
public interface ClientUserLoginLogDOMapper {
    int insert(ClientUserLoginLogDO record);

    int insertSelective(ClientUserLoginLogDO record);

    /**
      * @Author: zhoujiahao
      * @Description:  通过用户token 和登录agent信息获取登录信息
      * @Date: 15:17 2018-06-04
      * @return
      */
    ClientUserLoginLogDO selectByTokenAndAgent(@Param(value = "loginToken")String loginToken,@Param(value = "userAgent")String userAgent,@Param(value = "loginStatus") String loginStatus);

    /**
     *  更新用户登录信息
     */
    int updateByTokenAndAgentSelective(ClientUserLoginLogDO record);
    
    ClientUserLoginLogDO lastLoginInfo(@Param("userId") Long userId);
    
    /**
     * 
    * @Function: ClientUserLoginLogDOMapper.java
    * @Description: 统计一个自然月内登录成功6次及以上
    * @param:TODO
    * @return：int
    * @author: mazy
    * @date: 2018年10月12日 下午3:25:47 
    *
     */
    int commonIPCount(@Param("userId") Long userId,@Param("loginCity") String loginCity);
    

}