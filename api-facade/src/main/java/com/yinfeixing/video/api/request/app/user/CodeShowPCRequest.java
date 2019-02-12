package com.cf.api.request.app.user;

import com.cf.entity.ToString;
import com.cf.utils.validate.Query;
import com.cf.utils.validate.Remark;

import javax.validation.constraints.NotNull;

public class CodeShowPCRequest extends ToString {

	private static final long serialVersionUID = -9039664137312327711L;
	@Remark("账号")
	@NotNull(message="account不能为空",groups=Query.class)
	private String account;

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
