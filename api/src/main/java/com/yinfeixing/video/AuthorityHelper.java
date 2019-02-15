package com.yinfeixing.video;


import com.yinfeixing.utils.net.IP;
import com.yinfeixing.utils.web.WebHelper;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.client.ClientInfoDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Authority
 *
 * @author xulh
 * @date 2017/3/30
 * @description
 */
public class AuthorityHelper {
    public static ClientInfoDTO getClientInfo(HttpServletRequest request, APIRequest apiRequest) {
        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        clientInfoDTO.setClientIp(IP.getIpAddress(request));
        clientInfoDTO.setClientSymbol(WebHelper.getUseAgent(request));
        clientInfoDTO.setOriginalClientIp(WebHelper.getRemoteAddr());
        String originalClientSymbol = clientInfoDTO.getClientSymbol();
        if(apiRequest.getSource() == null){
        	apiRequest.setSource("WEB");
        }
        switch (apiRequest.getSource()) {
		case "ANDROID":
			originalClientSymbol = apiRequest.getDeviceId();
			break;
		case "IOS":
			originalClientSymbol = apiRequest.getDeviceId();
			break;
		default:
			break;
		}
        clientInfoDTO.setOriginalClientSymbol(originalClientSymbol);
        return clientInfoDTO;
    }
}