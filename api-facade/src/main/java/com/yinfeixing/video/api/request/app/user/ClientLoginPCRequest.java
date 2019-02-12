package com.cf.api.request.app.user;

import com.cf.utils.validate.Query;
import com.cf.utils.validate.Remark;

import javax.validation.constraints.NotNull;

public class ClientLoginPCRequest extends ClientLoginRequest {

	private static final long serialVersionUID = -137675322203718578L;

	@Remark("图片验证码可以为空")
    private String imgCode;

	@Remark("UUID")
	@NotNull(message="uuid不能为空",groups=Query.class)
	private String uuid;
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
