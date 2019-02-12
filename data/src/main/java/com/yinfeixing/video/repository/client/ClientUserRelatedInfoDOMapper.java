package com.yinfeixing.video.repository.client;

import com.cf.pms.dataconfig.annotation.PMSDB;
import com.yinfeixing.video.dataobject.client.ClientUserRelatedInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@PMSDB
public interface ClientUserRelatedInfoDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUserRelatedInfoDO record);

    int insertSelective(ClientUserRelatedInfoDO record);

    ClientUserRelatedInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserRelatedInfoDO record);

    int updateByPrimaryKey(ClientUserRelatedInfoDO record);

    /**
     * @Author: zhoujiahao
     * @Description: 通过用户ID 获取用户租约信息
     * @Date: 12:15 2018-05-31
     * @param userId
     * @return
     */
    List<ClientUserRelatedInfoDO> queryUserRelatedList(@Param(value = "userId")Long userId,@Param(value = "relatedType")String relatedType, @Param("currentRelateId") Long currentRelateId);

    List<ClientUserRelatedInfoDO> queryUserRelatedListEqual(@Param(value = "relatedType")String relatedType, @Param("currentRelateId") Long currentRelateId);


    /**
     * @Author: zhoujiahao
     * @Description: 批量插入用户关联信息
     * @Date: 13:50 2018-05-31
     * @param clientUserRelatedInfoDOS
     */
    int insertBatchUserRelatedInfo(@Param(value = "list")List<ClientUserRelatedInfoDO> clientUserRelatedInfoDOS);

    int deleteAcount(@Param(value = "userId") Long userId);

    List<ClientUserRelatedInfoDO> queryUserRelatedListWithout(@Param("userId") Long userId, @Param("relatedType") String relatedType, @Param("currentRelateId") Long currentRelateId);

    /**
     * @Author: zjh
     * @Description: 根据关联ID和类型获取用户关联信息
     * @Date: 17:54 2018-11-22
     * @return
     */
    List<ClientUserRelatedInfoDO> queryUserIdByRelatedIds(@Param(value = "relatedIds")List<Long> relatedIds, @Param(value = "relatedType")String relatedType);
}