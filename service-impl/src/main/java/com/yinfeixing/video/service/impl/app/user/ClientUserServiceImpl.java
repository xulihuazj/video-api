package com.yinfeixing.video.service.impl.app.user;

import com.yinfeixing.video.repository.client.ClientUserAccountDOMapper;
import com.yinfeixing.video.repository.client.ClientUserInfoDOMapper;
import com.yinfeixing.video.repository.client.ClientUserPasswordDOMapper;
import com.yinfeixing.video.request.APIRequest;
import com.yinfeixing.video.request.app.user.ClientRegisterRequest;
import com.yinfeixing.video.response.APIResponse;
import com.yinfeixing.video.response.app.user.RegisteredResponse;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@Repository
public class ClientUserServiceImpl implements ClientUserService {

    private static final Logger logger = LogManager.getLogger(ClientUserServiceImpl.class);

    @Resource
    private TransactionTemplate pmsDBTransactionTemplate;
    @Resource
    private ClientUserAccountDOMapper clientUserAccountDOMapper;
    @Resource
    private ClientUserInfoDOMapper clientUserInfoDOMapper;
    @Resource
    private ClientUserPasswordDOMapper clientUserPasswordDOMapper;

    @Override
    public APIResponse<RegisteredResponse> clientRegister(APIRequest<ClientRegisterRequest> request) {
//        LogHelper.info(logger, "【客户端】【用户注册】，请求参数={0}", request);
//        ClientRegisterRequest bizRequest = request.getBizRequest(ClientRegisterRequest.class);
//        Assert.isTrue("PHONE".equals(bizRequest.getAccountType()) && !ValidUtil.validChinaPhone(bizRequest.getAccount()),
//                "请输入正确的手机号");
//        Assert.isTrue("PHONE".equals(bizRequest.getAccountType()) && StringUtils.isNotBlank(bizRequest.getSmsCode()), "请输入正确的验证码");
//        Assert.isTrue("EMAIL".equals(bizRequest.getAccountType()) && !ValidUtil.validEmail(bizRequest.getAccount()),
//                "请输入正确的邮箱号");
//        Assert.isTrue("EMAIL".equals(bizRequest.getAccountType()) && StringUtils.isNotBlank(bizRequest.getIdentifyCode()), "请输入正确的验证码");
//        Assert.isTrue(!ValidUtil.passwordValid(bizRequest.getPassword()), "密码格式不正确");
//        // 判断当前用户是否
//        ClientUserAccountDO clientUserAccount = clientUserAccountDOMapper.selectAccountByCretTypeAndNum(bizRequest.getAccountType(), bizRequest.getAccount());
//        Assert.isTrue(clientUserAccount != null, "当前账号已经注册");
//        RegisteredResponse response = pmsDBTransactionTemplate.execute(transactionStatus -> {
//            Long userId = saveUserInfo(bizRequest.getAccount(), bizRequest.getPassword(), bizRequest.getAccountType());
//            RegisteredResponse result = new RegisteredResponse() {{
//                setUserId(userId);
//            }};
//            return result;
//        });
//        return APIResponse.instance(response);
        return null;
    }

    /**
     * @Description: 保存用户基本信息
     * @Date: 16:46 2018-05-31
     */
//    public Long saveUserInfo(String accountCert, String password, String accountType) {
//        ClientUserInfoDO clientUserInfo = new ClientUserInfoDO();
//        if (!AccountTypeEnum.WEIXIN.getCode().equalsIgnoreCase(accountType)) {
//            clientUserInfo.setUserPhone(accountCert);
//        }
//        clientUserInfo.setUserStatus(CommonStatusEnum.EFFECTIVE.getCode());
//        clientUserInfoDOMapper.insertSelective(clientUserInfo);
//        Long userId = clientUserInfo.getUserId();
//        ClientUserAccountDO clientUserAccount = new ClientUserAccountDO() {{
//            setAccountCert(accountCert);
//            setAccountType(accountType);
//            setUserId(userId);
//        }};
//        clientUserAccountDOMapper.insertSelective(clientUserAccount);
//        // 插入账号密码表
//        ClientUserPasswordDO clientUserPassword = new ClientUserPasswordDO() {{
//            setPasswordType(PasswordTypeEnum.LOGIN.getCode());
//            setUserId(clientUserInfo.getUserId());
//            setPassword(SecurityHelper.MD5(SecurityHelper.SHA1(password)).toLowerCase());
//        }};
//        clientUserPasswordDOMapper.insertSelective(clientUserPassword);
//        return userId;
//    }


}
