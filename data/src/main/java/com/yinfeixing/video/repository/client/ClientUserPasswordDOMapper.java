package com.yinfeixing.video.repository.client;

import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
import com.yinfeixing.video.dataobject.client.ClientUserPasswordDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@VIDEODB
public interface ClientUserPasswordDOMapper {
    int insert(ClientUserPasswordDO record);

    int insertSelective(ClientUserPasswordDO record);

    /**
     * @param userId
     * @Author: xulh
     * @Description: t通过用户ID获取用户密码
     * @Date: 12:02 2018-05-31
     */
    ClientUserPasswordDO selectPasswordByUserId(@Param(value = "userId") Long userId);

    /**
     * @param clientUserPasswordDO
     * @return
     * @Author: xulh
     * @Description: 通过用户ID 修改用户密码
     * @Date: 11:11 2018-06-05
     */
    int updateSelective(ClientUserPasswordDO clientUserPasswordDO);

    int deleteAcount(@Param(value = "userId") Long userId);

    /**
     * @param clientUserPasswordDOS
     * @return
     * @Author: xulh
     * @Description: 批量插入用户密码信息表
     * @Date: 13:37 2018-07-16
     */
    int insertUserPasswordBatch(@Param(value = "list") List<ClientUserPasswordDO> clientUserPasswordDOS);

}