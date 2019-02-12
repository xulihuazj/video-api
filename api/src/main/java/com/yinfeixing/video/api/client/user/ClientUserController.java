package com.yinfeixing.video.api.client.user;

import com.yinfeixing.video.api.BaseController;
import com.yinfeixing.video.api.system.SystemType;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/list")
    public void clientList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
//        APIRequest<ClientUserListRequest> apiRequest = super.getObjectByRequest(ClientUserListRequest.class,
//                httpRequest, Query.class);
        super.success("11111", httpRequest, httpResponse);
    }

}