package com.yinfeixing.video.service.app.user;

import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.user.ClientRegisterRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.user.RegisteredResponse;

/**
 * @author xulh on 2018-05-31.
 */
public interface ClientUserService {

    /**
      * @Author: xulh
      * @Description: 用户注册
      * @Date: 11:07 2018-05-31
      * @param request
      * @return
      */
    APIResponse<RegisteredResponse> clientRegister(APIRequest<ClientRegisterRequest> request);
//
//    /**
//      * @Author: xulh
//      * @Description: 用户登录：手机号+密码和手机号+验证码
//      * @Date: 16:53 2018-06-04
//      * @param request
//      * @return
//      */
//    APIResponse<UserLoginResponse> userLogin(APIRequest<ClientLoginRequest> request);
//
//    /**
//      * @Author: xulh
//      * @Description: 第三方登录
//      * @Date: 19:46 2018-06-04
//      * @param request
//      * @return
//      */
//    APIResponse<UserLoginResponse> thirdLogin(APIRequest<ThirdLoginRequest> request);
//
//    /**
//     * @Author: xulh
//     * @Description: 重置密码
//     * @Date: 19:46 2018-06-04
//     * @param request
//     * @return
//     */
//    APIResponse<RegisteredResponse> passwordReset(APIRequest<RegisteredRequest> request);
//
//    /**
//     * @Author: xulh
//     * @Description: 手机换绑或者绑定,微信绑定
//     * @Date: 19:46 2018-06-04
//     * @param request
//     * @return
//     */
//    APIResponse<RegisteredResponse> bindAccount(APIRequest<BindAccountRequest> request);
//
//    /**
//     * @Author: xulh
//     * @Description: 查询当前用户生效租约的房间信息
//     * @Date: 17:16 2018-06-06
//     * @return
//     */
//     APIResponse<UserDashboardInfoResponse> queryDashboard();
//
//    /**
//      * @Author: xulh
//      * @Description: 用户自动登录
//      * @Date: 14:34 2018-06-07
//      * @return
//      */
//    UserLoginResponse autoLogin(APIRequest<ClientLoginRequest> request);
//
//    /**
//     * @Author: xulh
//     * @Description: 用户修改信息
//     * @Date: 14:34 2018-06-07
//     * @return
//     */
//    APIResponse<RegisteredResponse> update(APIRequest<UserUpdateRequest> request);
//
//	    /**
//      * @Author: xulh
//      * @Description: 目前用于微信绑定： 获取绑定的APPiD
//      * @Date: 16:39 2018-09-25
//      * @param request
//      * @return
//      */
//   APIResponse<UserThirdBindPreResponse> ThirdBindPre(APIRequest<BindAccountRequest> request);
//
//    /**
//     * PC:重置密码(无需手机验证码)
//     * @param request
//     * @return
//     */
//    APIResponse<RegisteredResponse> passwordResetPC(APIRequest<ModifyPasswordPCRequest> request);


}
