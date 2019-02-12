package com.yinfeixing.video.repository.client;

import com.cf.pms.dataconfig.annotation.PMSDB;
import com.yinfeixing.video.dataobject.client.ClientUserLeaseDataCorrelationDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.yinfeixing.video.model.clientuser.ClientUserStatusCountDO;

@PMSDB
public interface ClientUserLeaseDataCorrelationDOMapper {
	int deleteByPrimaryKey(Integer userLeaseDataCorrelationId);

	int insert(ClientUserLeaseDataCorrelationDO record);

	int insertSelective(ClientUserLeaseDataCorrelationDO record);

	ClientUserLeaseDataCorrelationDO selectByPrimaryKey(Integer userLeaseDataCorrelationId);

	int updateByPrimaryKeySelective(ClientUserLeaseDataCorrelationDO record);

	int updateByPrimaryKey(ClientUserLeaseDataCorrelationDO record);

	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 搜索全部用户(新用户+所属项目用户)
	 * @param:TODO
	 * @return：List<Long>
	 * @author: mazy
	 * @date: 2019年1月4日 下午4:31:05
	 *
	 */
	List<Long> searchAll(@Param("userIds") List<Long> userIds, @Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 查询新用户，可以通过userIds过滤，不传则查所有
	 * @param:TODO
	 * @return：List<Long>
	 * @author: mazy
	 * @date: 2019年1月4日 下午4:54:16
	 *
	 */
	List<Long> searchNewUser(@Param("userIds") List<Long> userIds);

	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 查询所属项目下签约用户
	 * @param:TODO
	 * @return：List<Long>
	 * @author: mazy
	 * @date: 2019年1月4日 下午5:05:49
	 *
	 */
	List<Long> searchSign(@Param("userIds") List<Long> userIds, @Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 查询在住
	 * @param:TODO
	 * @return：List<Long>
	 * @author: mazy
	 * @date: 2019年1月4日 下午6:25:10
	 *
	 */
	List<Long> searchUnderway(@Param("userIds") List<Long> userIds, @Param("projectId") Integer projectId);
	
	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 查询历史住客
	 * @param:TODO
	 * @return：List<Long>
	 * @author: mazy
	 * @date: 2019年1月4日 下午6:54:00
	 *
	 */
	List<Long> searchHistory(@Param("userIds") List<Long> userIds, @Param("projectId") Integer projectId);
	
	/**
	 * 
	 * @Function: ClientUserLeaseDataCorrelationDOMapper.java
	 * @Description: 统计用户身份状态
	 * @param:TODO
	 * @return：List<ClientUserStatusCountDO>
	 * @author: mazy
	 * @date: 2019年1月7日 上午10:42:58
	 *
	 */
	List<ClientUserStatusCountDO> userStatusCount(@Param("userIds")List<Long> userIds);

	int updateStatusBYuserIdAndServiceId(@Param("userId") Long userId, @Param("subjectId") Long subjectId,
			@Param("renterId") Long renterId);
	
	int updateServiceStatusBYuserIdAndServiceId(@Param("renterIdList") List<Long> renterIdList,@Param("subjectId") Long subjectId);
}