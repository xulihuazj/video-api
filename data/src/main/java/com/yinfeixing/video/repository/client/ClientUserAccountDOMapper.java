package com.yinfeixing.video.repository.client;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.client.ClientUserAccountDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@VIDEODB
public interface ClientUserAccountDOMapper {
    int insert(ClientUserAccountDO record);

    int insertSelective(ClientUserAccountDO record);

    /**
     * @param accountType，accountCert
     * @Author: xulh
     * @Description: 通过证件号和证件类型查询账户信息
     * @Date: 12:06 2018-05-31
     */
    ClientUserAccountDO selectAccountByCretTypeAndNum(@Param(value = "accountType") String accountType, @Param(value = "accountCert") String accountCert);

    /**
     * @param clientUserAccountDO
     * @return
     * @Author: xulh
     * @Description: 更新账号信息
     * @Date: 16:56 2018-06-05
     */
    int updateAccountSelective(ClientUserAccountDO clientUserAccountDO);

    /**
     * @param accountType，accountCert，newAccountCert
     * @return
     * @Author: xulh
     * @Description: 通过账号和类型修改账号
     * @Date: 17:35 2018-06-05
     */
    int updateAccoutByCretTypeAndNum(@Param(value = "accountType") String accountType, @Param(value = "accountCert") String accountCert, @Param(value = "newAccountCert") String newAccountCert);

    /**
     * @return
     * @Author: zhoujiahao
     * @Description: 删除手机号测试用
     * @Date: 13:40 2018-06-07
     */
    int deleteAcount(@Param(value = "userId") Long userId);

    /**
     * @param accountType,userId
     * @return
     * @Author: zhoujiahao
     * @Description:通过用户ID和账号类型获取账号信息
     * @Date: 19:13 2018-06-25
     */
    ClientUserAccountDO selectAccoutnByCretTypeAndUserId(@Param(value = "accountType") String accountType, @Param(value = "userId") Long userId);

    /**
     * @param userId,accountType
     * @return
     * @Author: zhoujiahao
     * @Description: 通过账号类型和用户ID删除用户账号
     * @Date: 16:46 2018-06-27
     */
    int deleteAccountByUserIdAndAccountType(@Param(value = "userId") Long userId, @Param(value = "accountType") String accountType);

    /**
     * @param clientUserAccountDOS
     * @return
     * @Author: zhoujiahao
     * @Description: 批量插入用户账号信息表
     * @Date: 13:37 2018-07-16
     */
    int insertUserAccountBatch(@Param(value = "list") List<ClientUserAccountDO> clientUserAccountDOS);

    /**
     * @return
     * @Author: zjh
     * @Description: 批量更新账户手机号
     * @Date: 17:33 2018-11-22
     */
    int updateUserAccountBatch(@Param(value = "list") List<ClientUserAccountDO> clientUserAccountDOS, @Param(value = "accountType") String accountType);

}