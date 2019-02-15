package com.yinfeixing.video.request.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Query;
import com.yinfeixing.utils.validate.RE;
import com.yinfeixing.utils.validate.Remark;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CheckMobileCodeRequest extends ToString {

	private static final long serialVersionUID = -4484025167378121344L;
	
	@Remark("手机号")
	@NotNull(message = "手机号不能为空")
	@Pattern(regexp = RE.MOBILE,message = "手机号格式不正确")
	private String mobile;

	@Remark("验证码")
	@NotNull(message="验证码不能为空",groups= Query.class)
	private String code;

	@Remark("FORGET:重置密码,ACTIVITY_CODE:活动验证码")
	private String serviceType;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	
}
