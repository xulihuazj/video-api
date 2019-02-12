package com.yinfeixing.video.api;

import com.cf.api.enums.common.Source;
import com.cf.api.request.APIRequest;
import com.cf.api.request.ClientInfoDTO;
import com.cf.utils.net.IP;
import com.cf.utils.web.WebHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Authority
 *
 * @author liwei
 * @date 2017/3/30
 * @description
 */
public class AuthorityHelper {
    public static ClientInfoDTO getClientInfo(HttpServletRequest request, APIRequest apiRequest) {
        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        clientInfoDTO.setClientIp(IP.getIpAddress(request));
        clientInfoDTO.setClientSymbol(WebHelper.getUseAgent(request));
        // mazy 20180929 修复了这个方法不能正确获取客户端信息的bug
        clientInfoDTO.setOriginalClientIp(WebHelper.getRemoteAddr());
        String originalClientSymbol = clientInfoDTO.getClientSymbol();
        if(apiRequest.getSource() == null){
        	apiRequest.setSource(Source.WEB);
        }
        switch (apiRequest.getSource()) {
		case ANDROID:
			originalClientSymbol = apiRequest.getDeviceId();
			break;
		case IOS:
			originalClientSymbol = apiRequest.getDeviceId();
			break;
		default:
			break;
		}
        clientInfoDTO.setOriginalClientSymbol(originalClientSymbol);
        return clientInfoDTO;
    }
}
