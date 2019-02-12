package com.yinfeixing.video.api.response.app.user;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.utils.validate.Remark;

public class CodeShowPCResponse extends ToString {

	private static final long serialVersionUID = 4904182450356609847L;
	
	@Remark("SHOW:必须展示图形验证码，HIDE:无需展示图形验证码")
	private String codeShow;

	@Remark("说明")
	private String explain;

	public String getCodeShow() {
		return codeShow;
	}
	public void setCodeShow(String codeShow) {
		this.codeShow = codeShow;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
