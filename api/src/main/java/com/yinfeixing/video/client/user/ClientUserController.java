package com.yinfeixing.video.client.user;

import com.yinfeixing.utils.validate.Add;
import com.yinfeixing.video.BaseController;
import com.yinfeixing.video.core.jpa.UserJpaRepository;
import com.yinfeixing.video.dataobject.client.ClientUserInfoDO;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.user.ClientRegisterRequest;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/client/user")
public class ClientUserController extends BaseController {

    @Resource
    private ClientUserService clientUserServiceImpl;
    @Resource
    private UserJpaRepository userJpaRepository;


    /**
     * 账号注册
     *
     * @param httpRequest
     * @param httpResponse
     * @throws Exception
     */
    @PostMapping("/register")
    public void clientRegister(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        APIRequest<ClientRegisterRequest> apiRequest = super.getObjectByRequest(ClientRegisterRequest.class,
                httpRequest, Add.class);
//        Optional<ClientUserInfoDO> userOptional = userJpaRepository.findById(1L);
//        System.out.println(userOptional.isPresent());
        super.success(clientUserServiceImpl.clientRegister(apiRequest), httpRequest, httpResponse);
    }


}