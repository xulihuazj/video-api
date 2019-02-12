package com.cf.pms.service.impl.app.user;

import com.cf.api.LocalContextHolder;
import com.cf.api.dto.app.user.UserInfoDTO;
import com.cf.api.dto.order.ThirdLoginDTO;
import com.cf.api.enums.app.order.OrderChannelEnum;
import com.cf.api.enums.app.order.PaymentProjectTypeEnum;
import com.cf.api.enums.app.room.UserRelatedTypeEnum;
import com.cf.api.enums.app.user.LoginTypeEnum;
import com.cf.api.enums.app.user.PasswordTypeEnum;
import com.cf.api.enums.common.BusinessTypeEnum;
import com.cf.api.enums.lease.RenterStatusEnum;
import com.cf.api.enums.system.SystemApiTypeEnum;
import com.cf.api.enums.user.AccountTypeEnum;
import com.cf.api.request.APIRequest;
import com.cf.api.request.ClientInfoDTO;
import com.cf.api.request.app.user.*;
import com.cf.api.response.APIResponse;
import com.cf.api.response.app.user.*;
import com.cf.pms.ExternalUserInfoModel;
import com.cf.pms.common.Constant;
import com.cf.pms.core.app.user.ClientUserInfoRepository;
import com.cf.pms.core.door.RoomDoorLockRepository;
import com.cf.pms.core.lease.LeaseRepository;
import com.cf.pms.core.prepay.PrepayRepository;
import com.cf.pms.core.project.ProjectRepository;
import com.cf.pms.core.redis.RedisRepository;
import com.cf.pms.core.renter.RenterRepository;
import com.cf.pms.core.room.RoomRepository;
import com.cf.pms.core.sms.SmsLogRepository;
import com.cf.pms.core.user.UserLoginInfoRepository;
import com.cf.pms.enums.common.CommonStatusEnum;
import com.cf.pms.enums.doorlock.DoorlockStatusEnum;
import com.cf.pms.enums.doorlock.PasswordType;
import com.cf.pms.enums.prepay.PrepayServiceType;
import com.cf.pms.enums.prepay.PrepayTypeEnum;
import com.cf.pms.enums.sms.SmsServiceTypeEnum;
import com.cf.pms.enums.sms.SmsValidationStatusEnum;
import com.cf.pms.enums.user.UserStatus;
import com.cf.pms.error.BizErrorCode;
import com.cf.pms.error.SystemErrorCode;
import com.cf.pms.exception.BusinessException;
import com.cf.pms.model.app.room.ClientUserRelatedInfoModel;
import com.cf.pms.model.app.user.ClientUserAccountModel;
import com.cf.pms.model.app.user.ClientUserInfoModel;
import com.cf.pms.model.app.user.ClientUserPasswordModel;
import com.cf.pms.model.common.PaymentConfigInfoModel;
import com.cf.pms.model.door.RoomDoorLockModel;
import com.cf.pms.model.door.RoomDoorPasswordModel;
import com.cf.pms.model.lease.AccountPeriodModel;
import com.cf.pms.model.lease.LeaseRenterModel;
import com.cf.pms.model.lease.LeaseRoomModel;
import com.cf.pms.model.prepay.LeaseRoomPrepayTotalModel;
import com.cf.pms.model.project.HotelProjectModel;
import com.cf.pms.model.room.RoomInfoModel;
import com.cf.pms.model.sms.SmsLogModel;
import com.cf.pms.model.user.UserLoginInfoModel;
import com.cf.pms.service.app.user.ClientUserService;
import com.cf.pms.service.image.ImgCodeService;
import com.cf.pms.service.impl.system.ExternalValidPrivilege;
import com.cf.pms.service.pay.PayService;
import com.cf.pms.service.pay.PaymentConfigService;
import com.cf.pms.service.pmsclientuser.PmsClientUserService;
import com.cf.pms.service.prepay.PrepayService;
import com.cf.pms.service.sms.SmSService;
import com.cf.pms.service.system.TokenHelpService;
import com.cf.utils.Date.DateUtils;
import com.cf.utils.common.SenInfoProcessUtil;
import com.cf.utils.common.UnitConvertUtil;
import com.cf.utils.common.ValidUtil;
import com.cf.utils.emojiUtils.EmojiUtils;
import com.cf.utils.json.JSONHelper;
import com.cf.utils.log.LogHelper;
import com.cf.utils.modeldtoconvert.CachedBeanCopier;
import com.cf.utils.net.IP;
import com.cf.utils.security.CharacterMixUtil;
import com.cf.utils.security.SecurityCode;
import com.cf.utils.security.SecurityHelper;
import com.cf.utils.sms.SMSEnum;
import com.cf.utils.web.IPQueryUtil;
import com.cf.utils.web.WebHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClientUserServiceImpl.java 1.0.0 2018-05-31 11:08 Copyright © 2014-2018,52mamahome.com.All rights reserved history : 1. 2018-05-31 11:08 @author
 * zhoujiahao
 */
@Service
public class ClientUserServiceImpl implements ClientUserService {

    private Logger logger = LogManager.getLogger(ClientUserServiceImpl.class);

}
