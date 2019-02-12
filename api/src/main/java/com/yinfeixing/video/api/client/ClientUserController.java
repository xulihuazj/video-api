package com.yinfeixing.video.api.client;


import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinfeixing.video.api.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pms/client")
public class ClientUserController extends BaseController {

    @Resource
    private ClientUserService clientUserServiceImpl;

    @GetMapping("/list")
    public void clientList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientUserListRequest> apiRequest = super.getObjectByRequest(ClientUserListRequest.class,
                httpRequest, Query.class);
        super.success(pmsClientUserService.searchClientUserList(apiRequest), httpRequest, httpResponse);
    }

    @GetMapping("/detail")
    public void ClientUserDetail(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientUserDetailRequest> apiRequest = super.getObjectByRequest(ClientUserDetailRequest.class,
                httpRequest, Query.class);
        super.success(this.pmsClientUserService.clientUserDetail(apiRequest), httpRequest, httpResponse);
    }

    @PostMapping("/bindAccount")
    @Idempotent
    public void bindAccount(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientUserChangeMobileBindingRequest> apiRequest = super.getObjectByRequest(
                ClientUserChangeMobileBindingRequest.class, httpRequest, Query.class);
        super.success(pmsClientUserService.bindAccount(apiRequest), httpRequest, httpResponse);
    }

    @PostMapping(value = "/valid/smsCode")
    @SystemType
    public void changePhoneValidCode(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws Exception {
        // 获取请求业务参数对象
        APIRequest<PhoneCodeVaildPCRequest> clientAPIRequest = super.getObjectByRequest(PhoneCodeVaildPCRequest.class,
                httpRequest, Add.class);
        super.success(clientUserServiceImpl.validNewPhoneCode(clientAPIRequest), httpRequest, httpResponse);
    }

}