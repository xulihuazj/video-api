package com.yinfeixing.video.api.client.user;

import com.yinfeixing.utils.validate.Add;
import com.yinfeixing.video.api.BaseController;
import com.yinfeixing.video.api.request.APIRequest;
import com.yinfeixing.video.api.request.app.user.ClientRegisterRequest;
import com.yinfeixing.video.api.system.SystemType;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/client/user")
public class ClientUserController extends BaseController {

    @Resource
    private ClientUserService clientUserServiceImpl;


    /**
     * 账号注册
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @PostMapping("/register")
    public void clientRegister(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientRegisterRequest> apiRequest = super.getObjectByRequest(ClientRegisterRequest.class,
                httpRequest, Add.class);
        super.success(clientUserServiceImpl.clientRegister(apiRequest), httpRequest, httpResponse);
    }



}