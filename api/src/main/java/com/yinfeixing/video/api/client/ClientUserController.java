package com.yinfeixing.video.api.client;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinfeixing.video.api.BaseController;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientUserController extends BaseController {

    @Resource
    private ClientUserService clientUserServiceImpl;

    @GetMapping("/list")
    public void clientList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
//        APIRequest<ClientUserListRequest> apiRequest = super.getObjectByRequest(ClientUserListRequest.class,
//                httpRequest, Query.class);
//        super.success(pmsClientUserService.searchClientUserList(apiRequest), httpRequest, httpResponse);
    }

}