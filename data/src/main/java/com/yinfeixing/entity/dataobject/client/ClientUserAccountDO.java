package com.yinfeixing.entity.dataobject.client;


import com.yinfeixing.entity.DOBaseEntity;

public class ClientUserAccountDO extends DOBaseEntity {

    private Long userId;

    private String accountType;

    private String accountCert;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAccountCert() {
        return accountCert;
    }

    public void setAccountCert(String accountCert) {
        this.accountCert = accountCert == null ? null : accountCert.trim();
    }

}