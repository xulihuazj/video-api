package com.cf.api.response.app.user;

import com.cf.entity.ToString;
import com.cf.utils.validate.Remark;

public class UserDashboardInfoResponse extends ToString{
    private static final long serialVersionUID = 1800405596659886611L;

    @Remark("房间名称")
    private  String roomName;

    @Remark("房间余额")
    private  String prepayTotalAmount;

    @Remark("门锁密码")
    private  String doorPassword;

    @Remark("已扣水电费")
    private  String consumTotalAmount;

    @Remark("本期-已扣水电费")
    private String consumePeriodAmount;

    @Remark("租约ID")
    private  String LeaseId;

    @Remark("房间ID")
    private  String roomId;

    @Remark("租客信息ID")
    private  String RenterId;

    @Remark("租约房间ID")
    private  String leaseRoomId;

    public String getLeaseId() {
        return LeaseId;
    }

    public void setLeaseId(String leaseId) {
        LeaseId = leaseId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getLeaseRoomId() {
        return leaseRoomId;
    }

    public void setLeaseRoomId(String leaseRoomId) {
        this.leaseRoomId = leaseRoomId;
    }

    public String getRenterId() {
        return RenterId;
    }

    public void setRenterId(String renterId) {
        RenterId = renterId;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPrepayTotalAmount() {
        return prepayTotalAmount;
    }

    public void setPrepayTotalAmount(String prepayTotalAmount) {
        this.prepayTotalAmount = prepayTotalAmount;
    }

    public String getDoorPassword() {
        return doorPassword;
    }

    public void setDoorPassword(String doorPassword) {
        this.doorPassword = doorPassword;
    }

    public String getConsumTotalAmount() {
        return consumTotalAmount;
    }

    public void setConsumTotalAmount(String consumTotalAmount) {
        this.consumTotalAmount = consumTotalAmount;
    }

    public String getConsumePeriodAmount() {
        return consumePeriodAmount;
    }

    public void setConsumePeriodAmount(String consumePeriodAmount) {
        this.consumePeriodAmount = consumePeriodAmount;
    }
}
