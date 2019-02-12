package com.yinfeixing.entity.repository.client;

import com.cf.pms.dataconfig.annotation.PMSDB;
import com.yinfeixing.entity.dataobject.client.ClientUserInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@PMSDB
//@COMPANY(sysCompanyId = true)
public interface ClientUserInfoDOMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ClientUserInfoDO record);

    int insertSelective(ClientUserInfoDO record);

    ClientUserInfoDO selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ClientUserInfoDO record);

    int updateByPrimaryKey(ClientUserInfoDO record);

    int deleteAcount(@Param(value = "userId") Long userId);

    /**
      * @Author: zhoujiahao
      * @Description: 通过用户id集合查询用户信息
      * @Date: 17:32 2018-06-19
      * @param userIds
      * @return
      */
    List<ClientUserInfoDO> queryUserByUserIds(@Param(value = "userIds")List<Long> userIds);

    /**
      * @Author: zhoujiahao
      * @Description: 批量插入用户信息
      * @Date: 11:32 2018-07-16
      * @param clientUserInfoModels
      * @return
      */
    int insertUserInfoBatch(@Param(value = "list")List<ClientUserInfoDO> clientUserInfoModels);

    /**
     * @Author: zhoujiahao
     * @Description: 通过手机号
     * @Date: 11:32 2018-07-16
     * @return
     */
    List<ClientUserInfoDO> queryByAccountList(@Param(value = "accountList")List<String> accountList);

    /**
      * @Author: zjh
      * @Description: 批量更新用户信息
      * @Date: 16:59 2018-11-22
      * @return
      */
    int updateUserInfoBatch(@Param(value = "list")List<ClientUserInfoDO> clientUserInfoModels);
    
	List<Long> searchClientUser(@Param("mobileList") List<String> mobileList, @Param("userName") String userName,
			@Param("cretNum") String cretNum,@Param("cretType") String cretType);

}