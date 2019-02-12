package com.yinfeixing.entity.repository.client;

import com.yinfeixing.entity.dataconfig.annotation.VIDEODB;
import com.yinfeixing.entity.dataobject.client.ClientUserAccountDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@VIDEODB
public interface ClientUserAccountDOMapper {
    int insert(ClientUserAccountDO record);

    int insertSelective(ClientUserAccountDO record);

    /**
      * @Author: xulh
      * @Description: 通过证件号和证件类型查询账户信息
      * @Date: 12:06 2018-05-31
      * @param accountType，accountCert
      */
    ClientUserAccountDO  selectAccountByCretTypeAndNum(@Param(value = "accountType")String accountType,@Param(value = "accountCert") String accountCert);

    /**
      * @Author: xulh
      * @Description: 更新账号信息
      * @Date: 16:56 2018-06-05
      * @param clientUserAccountDO
      * @return
      */
    int updateAccountSelective(ClientUserAccountDO clientUserAccountDO);

    /**
      * @Author: xulh
      * @Description: 通过账号和类型修改账号
      * @Date: 17:35 2018-06-05
      * @param accountType，accountCert，newAccountCert
      * @return
      */
    int updateAccoutByCretTypeAndNum(@Param(value = "accountType")String accountType,@Param(value = "accountCert") String accountCert,@Param(value = "newAccountCert") String newAccountCert );

    /**
      * @Author: zhoujiahao
      * @Description: 删除手机号测试用
      * @Date: 13:40 2018-06-07
      * @return
      */
    int deleteAcount(@Param(value = "userId") Long userId);

    /**
      * @Author: zhoujiahao
      * @Description:通过用户ID和账号类型获取账号信息
      * @Date: 19:13 2018-06-25
      * @param accountType,userId
      * @return
      */
    ClientUserAccountDO selectAccoutnByCretTypeAndUserId(@Param(value = "accountType")String accountType,@Param(value = "userId") Long userId);

    /**
      * @Author: zhoujiahao
      * @Description: 通过账号类型和用户ID删除用户账号
      * @Date: 16:46 2018-06-27
      * @param userId,accountType
      * @return
      */
    int deleteAccountByUserIdAndAccountType(@Param(value = "userId")Long userId,@Param(value = "accountType")String accountType);

    /**
      * @Author: zhoujiahao
      * @Description: 批量插入用户账号信息表
      * @Date: 13:37 2018-07-16
      * @param clientUserAccountDOS
      * @return
      */
    int insertUserAccountBatch(@Param(value = "list") List<ClientUserAccountDO> clientUserAccountDOS);

    /**
      * @Author: zjh
      * @Description: 批量更新账户手机号
      * @Date: 17:33 2018-11-22
      * @return
      */
    int updateUserAccountBatch(@Param(value = "list") List<ClientUserAccountDO> clientUserAccountDOS,@Param(value = "accountType") String accountType );

}