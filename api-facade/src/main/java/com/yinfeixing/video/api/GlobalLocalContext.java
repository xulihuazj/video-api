package com.cf.api;

import com.cf.api.enums.common.Source;
import com.cf.utils.validate.Remark;

import java.util.List;

/**
 * @Description
 * @author xulh
 * @Date 2017年8月3日下午4:15:34
 */
public class GlobalLocalContext{
    
    @Remark("用户ID")
    private Long userId;

    @Remark("是否是白名单（是：WHITE,否：GENERAL）")
    private boolean isWhite;

    @Remark("来源")
    private Source source;

    @Remark("设备ID")
    private String deviceId;
    
    @Remark("版本信息")
    private String version;

    @Remark("用于接收来源信息")
    private String dtMonitor;
    
    @Remark("当前IP")
    private String currentIp;

    @Remark("当前用户所属公司的项目ID:内部系统不可空，移动端可空表示当前用户没有租约")
    private  Integer projectId;

    @Remark("当前项目类型，集中式、分散式(内部系统含有的字段)")
    private String businessType;
    
    @Remark("幂等校验ID（从请求头中取出）")
    private String requestId;

    @Remark("租约-房间ID（移动端含有的字段）,存放当前用户生效的")
    private Long leaseRoomId;

    @Remark("用户类型")
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getLeaseRoomId() {
        return leaseRoomId;
    }

    public void setLeaseRoomId(Long leaseRoomId) {
        this.leaseRoomId = leaseRoomId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDtMonitor() {
        return dtMonitor;
    }

    public void setDtMonitor(String dtMonitor) {
        this.dtMonitor = dtMonitor;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }


    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 扩展
     */
    private Long companyId;
    private String companyType;
    @Remark("该公司下所有公司ID，包含该公司ID")
    private List<Long> companyIds;
    @Remark("该公司下所有项目ID，包含该公司的项目ID")
    private List<Integer> projectIds;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }

    public List<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }
}
