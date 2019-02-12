/*
 * AlertServiceImpl.java 1.0.0 2018/7/4 9:34 Copyright © 2014-2017,52mamahome.com.All rights reserved history : 1. 2018/7/4 9:34 created by cyl
 */
package com.cf.pms.service.impl.alert;

import com.cf.api.LocalContextHolder;
import com.cf.api.dto.alert.*;
import com.cf.api.enums.app.room.UserRelatedTypeEnum;
import com.cf.api.enums.lease.NewLeaseStepEnum;
import com.cf.api.request.APIRequest;
import com.cf.api.request.alert.AddEvolveRequest;
import com.cf.api.request.alert.AlertInfoRequest;
import com.cf.api.request.alert.DataStatRequest;
import com.cf.api.request.alert.RiskRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.alert.*;
import com.cf.pms.common.Constant;
import com.cf.pms.core.alert.AlertInfoRepository;
import com.cf.pms.core.alert.AlertLogRepository;
import com.cf.pms.core.alert.ClientMessageRepository;
import com.cf.pms.core.alert.EvolveInfoRepository;
import com.cf.pms.core.app.user.ClientUserInfoRepository;
import com.cf.pms.core.common.CommonRepository;
import com.cf.pms.core.door.RoomDoorLockRepository;
import com.cf.pms.core.finance.BillTypeRepository;
import com.cf.pms.core.finance.LeaseRoomBillRepository;
import com.cf.pms.core.lease.LeaseRepository;
import com.cf.pms.core.renter.RenterRepository;
import com.cf.pms.core.room.RoomRepository;
import com.cf.pms.core.user.CompanyProjectRepository;
import com.cf.pms.core.user.UserRepository;
import com.cf.pms.core.yunding.device.DevElemeterRespository;
import com.cf.pms.core.yunding.device.DevWatermeterRespository;
import com.cf.pms.core.yunding.device.DeviceRepository;
import com.cf.pms.enums.alert.AlertStatusEnum;
import com.cf.pms.enums.alert.AlertTypeEnum;
import com.cf.pms.enums.alert.ClientMessageType;
import com.cf.pms.enums.bill.BillTypeCodeEnum;
import com.cf.pms.enums.bill.BillTypeEnum;
import com.cf.pms.enums.common.CommonStatusEnum;
import com.cf.pms.enums.common.ConfigKeyEnum;
import com.cf.pms.enums.finance.LeaseRoomBillBillBizTypeEnum;
import com.cf.pms.enums.finance.LeaseRoomBillBillStatusEnum;
import com.cf.pms.enums.lease.NewLeaseRoomStatusEnum;
import com.cf.pms.enums.renter.RenterStatusEnum;
import com.cf.pms.enums.room.SearchRoomStatusEnum;
import com.cf.pms.enums.sms.SmsSendStatusEnum;
import com.cf.pms.enums.yunding.device.DevTypeEnum;
import com.cf.pms.enums.yunding.home.BusinessTypeEnum;
import com.cf.pms.model.PageModel;
import com.cf.pms.model.PageResultModel;
import com.cf.pms.model.alert.AlertInfoLogModel;
import com.cf.pms.model.alert.AlertInfoModel;
import com.cf.pms.model.alert.EvolveInfoModel;
import com.cf.pms.model.app.msg.ClientMessageModel;
import com.cf.pms.model.app.room.ClientUserRelatedInfoModel;
import com.cf.pms.model.common.AppConfigModel;
import com.cf.pms.model.door.RoomDoorLockModel;
import com.cf.pms.model.finance.BillTypeModel;
import com.cf.pms.model.lease.LeaseInfoModel;
import com.cf.pms.model.lease.LeaseRenterModel;
import com.cf.pms.model.lease.LeaseRoomBillModel;
import com.cf.pms.model.lease.LeaseRoomModel;
import com.cf.pms.model.room.RoomInfoModel;
import com.cf.pms.model.user.CompanyProjectModel;
import com.cf.pms.model.user.SysUserModel;
import com.cf.pms.service.alert.AlertService;
import com.cf.pms.service.app.smart.SmartMessageService;
import com.cf.pms.service.door.PasswordService;
import com.cf.pms.yundingModel.device.DevElemeterInfoModel;
import com.cf.pms.yundingModel.device.DevInstallModel;
import com.cf.pms.yundingModel.device.DevWatermeterInfoModel;
import com.cf.utils.common.UnitConvertUtil;
import com.cf.utils.json.JSONHelper;
import com.cf.utils.log.LogHelper;
import com.cf.utils.modeldtoconvert.CachedBeanCopier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    private Logger logger = LogManager.getLogger(AlertServiceImpl.class);

    @Resource
    private LeaseRoomBillRepository leaseRoomBillRepository;
    @Resource
    private LeaseRepository leaseRepository;
    @Resource
    private AlertInfoRepository alertInfoRepository;
    @Resource
    private EvolveInfoRepository evolveInfoRepository;
    @Resource
    private DevElemeterRespository devElemeterRespository;
    @Resource
    private RoomDoorLockRepository roomDoorLockRepository;
    @Resource
    private DevWatermeterRespository devWatermeterRespository;
    @Resource
    private RoomRepository roomRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private RenterRepository renterRepository;
    @Resource
    private CompanyProjectRepository companyProjectRepository;
    @Resource
    private BillTypeRepository billTypeRepository;
    @Resource
    private CommonRepository commonRepository;
    @Resource
    private AlertLogRepository alertLogRepository;
    @Autowired
    private SmartMessageService smartMessageServiceImpl;
    @Autowired
    private ClientUserInfoRepository clientUserInfoRepositoryImpl;
    @Autowired
    private RenterRepository renterRepositoryImpl;
    @Resource
    private TransactionTemplate pmsDBTransactionTemplate;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private PasswordService passwordService;

    @Override
    public void alertEvent() throws Exception {
        LogHelper.info(logger, "【设备告警信息轮询】，AlertServiceImpl.alertEvent()");
        // 当前时间
        long nowTime = System.currentTimeMillis();
        // 上一次JOB执行时间
        AppConfigModel appConfigModel = this.commonRepository.queryConfigByConfigKey(ConfigKeyEnum.ALERT_JOB_TIME.getCode(), null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long jobTime = 0L;
        if (appConfigModel.getExtInfo() != null) {
            String extValue = appConfigModel.getExtInfo().get("lastJobRunTime");
            Date lastJobTime = sdf.parse(extValue);
            jobTime = lastJobTime.getTime();
        }
        // 两天前时间
        Date beforeTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beforeTime);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        beforeTime = calendar.getTime();
        // 电表离线2小时
        this.eleMeterAlertHandle(nowTime, jobTime, beforeTime);
        // 水表离线2小时
        this.waterMeterAlertHandle(nowTime, jobTime, beforeTime);
        // 门锁离线2小时
        this.doorLockAlertHandle(nowTime, jobTime, beforeTime);
        pmsDBTransactionTemplate.execute((transactionStatus) -> {
            Map<String, String> extInfo = new HashMap<>();
            extInfo.put("lastJobRunTime", sdf.format(new Date()));
            this.commonRepository.updateExtInfoById(appConfigModel.getId(), extInfo);
            return null;
        });
    }

    /**
     * 房间催租提醒
     */
    private void reminderOfRoomArrears() {
        // 处理中、未处理的 催租提醒告警信息
        Long startTime1 = System.currentTimeMillis();
        List<AlertInfoModel> dueAlertInfoModelList = this.alertInfoRepository.queryInfo(AlertTypeEnum.DUE.getCode(), Arrays.asList(
                AlertStatusEnum.WAITING.getCode(), AlertStatusEnum.IN_HANDLE.getCode()));
        System.out.println("第一段消耗时间：" + (System.currentTimeMillis() - startTime1));
        // 当前告警信息列表中存在的 未处理的 账单相关的 租约ID
        List<Long> leaseIdList = CollectionUtils.isNotEmpty(dueAlertInfoModelList) ?
                dueAlertInfoModelList.stream().map(AlertInfoModel::getLeaseId).collect(Collectors.toList()) : new ArrayList<>();
        List<Map<Long, List<Long>>> maps = new ArrayList<>();
        Long startTime = System.currentTimeMillis();
        if (CollectionUtils.isNotEmpty(dueAlertInfoModelList)) {
            dueAlertInfoModelList.forEach(alert -> {
                Map<Long, List<Long>> map = new HashMap<>();
                // 通过类型及租约ID查询告警信息列表的关联ID
                List<Long> relevanceIdList = this.alertInfoRepository.queryRelevanceByType(AlertTypeEnum.DUE.getCode(), alert.getLeaseId());
                map.put(alert.getLeaseId(), relevanceIdList);
                maps.add(map);
            });
        }
        System.out.println("第二段消耗时间：" + (System.currentTimeMillis() - startTime));
        // 当前时间
        long nowTime = System.currentTimeMillis();
        // 部分支付及未支付的 bill_status
        List<String> billStatusList = Arrays.asList(LeaseRoomBillBillStatusEnum.NO_PAYMENT.getCode(),
                LeaseRoomBillBillStatusEnum.PARTIAL_PAYMENT.getCode());
        // 根据code获取billTypeId列表，房费的bill_type_id
        List<Integer> billTypeIdList = this.billTypeRepository.queryBillTypeIdListByCode(BillTypeCodeEnum.RENT.getCode());
        // 有效的、部分支付及未支付的 房费账单
        List<LeaseRoomBillModel> leaseRoomBillModels = this.leaseRoomBillRepository.queryByBillTypeId(billStatusList,
                CommonStatusEnum.EFFECTIVE.getCode(), billTypeIdList);
        if (CollectionUtils.isNotEmpty(leaseRoomBillModels)) {
            for (LeaseRoomBillModel leaseRoomBillModel : leaseRoomBillModels) {
                Long leaseId = leaseRoomBillModel.getLeaseId();
                Integer account = leaseRoomBillModel.getAccountPeriod();
                LeaseInfoModel leaseBaseModel = this.leaseRepository.queryDueInfo(leaseId, NewLeaseStepEnum.LEASE.getCode());
                if (leaseBaseModel != null) {
                    Integer projectId = leaseBaseModel.getProjectId();
                    // 获取租约提醒天数
                    Integer remindTime = leaseBaseModel.getRemindValue();
                    // 应付时间
                    Date planPayTime = leaseRoomBillModel.getPlanPayTime();
                    // 计算时差
                    long planTime = planPayTime.getTime();
                    long differTime = (planTime - nowTime) / (1000 * 60 * 60 * 24);
                    /**
                     *  此lease_room_info相关的账单,start_time大于退房时间、未支付的账单会被添加标记，在下一工作台告警JOB中不再展示
                     *  modify by xulihua
                     *  modify time  2019/01/29
                     */
                    Map<String, String> extInfo = leaseRoomBillModel.getExtInfo();
                    if (differTime <= remindTime && (null == extInfo || null == extInfo.get("check_out_bill"))) { // 触发告警
                        if (CollectionUtils.isNotEmpty(leaseIdList)) {
                            //判断 告警信息列表是否已经包含了这个租约
                            if (leaseIdList.contains(leaseId)) {
                                for (Long id : leaseIdList) {
                                    if (id.equals(leaseId)) {
                                        for (Map<Long, List<Long>> m : maps) {
                                            if (m.keySet().contains(id)) {
                                                List<Long> values = m.get(id);
                                                if (!values.contains(account.longValue())) {
                                                    //创建新告警
                                                    createNewAlert(null, leaseId, leaseBaseModel.getSysCompanyId(), projectId, new Date(), AlertTypeEnum.DUE.getCode(),
                                                            planPayTime, null,
                                                            account.longValue(), leaseBaseModel.getContractNum(), null);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                //创建新告警
                                createNewAlert(null, leaseId, leaseBaseModel.getSysCompanyId(), projectId, new Date(), AlertTypeEnum.DUE.getCode(), planPayTime, null,
                                        account.longValue(), leaseBaseModel.getContractNum(), null);
                            }
                        } else {
                            //创建新告警
                            createNewAlert(null, leaseId, leaseBaseModel.getSysCompanyId(), projectId, new Date(), AlertTypeEnum.DUE.getCode(), planPayTime, null,
                                    account.longValue(), leaseBaseModel.getContractNum(), null);
                        }
                    }
                }
            }
            System.out.println("第三段消耗时间：" + (System.currentTimeMillis() - nowTime));
        }
    }

    /**
     * 应退未退告警
     */
    private void warningShouldBeWithdrawn() {
        List<AlertInfoModel> noCheckoutAlertInfoList = this.alertInfoRepository.queryInfo(AlertTypeEnum.NO_CHECKOUT.getCode(), Arrays.asList(
                AlertStatusEnum.WAITING.getCode(), AlertStatusEnum.IN_HANDLE.getCode()));
        List<Long> leasess = CollectionUtils.isNotEmpty(noCheckoutAlertInfoList) ?
                noCheckoutAlertInfoList.stream().map(AlertInfoModel::getLeaseId).collect(Collectors.toList()) : new ArrayList<>();
        List<LeaseInfoModel> baseModels = this.leaseRepository.selectCheckoutRoom();
        if (CollectionUtils.isNotEmpty(baseModels)) {
            for (LeaseInfoModel leaseBaseModel : baseModels) {
                Date endTime = leaseBaseModel.getEndTime();
                Long leaseId = leaseBaseModel.getLeaseId();
                List<LeaseRoomModel> leaseRoomModels = this.leaseRepository.queryByLeaseIdsAndStatus(Arrays.asList(leaseId), CommonStatusEnum.EFFECTIVE.getCode());
                if (CollectionUtils.isNotEmpty(leaseRoomModels)) {
                    if (!leasess.contains(leaseId)) {
                        createNewAlert(null, leaseId, leaseBaseModel.getSysCompanyId(), leaseBaseModel.getProjectId(), new Date(), AlertTypeEnum.NO_CHECKOUT.getCode(), endTime,
                                null, null, leaseBaseModel.getContractNum(), null);
                    }
                }
            }
        }
    }

    @Override
    public void alertRoom() {
        LogHelper.info(logger, "【房间催租提醒、应退未退告警信息轮询】，AlertServiceImpl.alertRoom()");
        // 催租提醒
        this.reminderOfRoomArrears();
        // 应退未退
        this.warningShouldBeWithdrawn();
    }

    /**
     * 添加新的告警或者打开原来的告警
     *
     * @param roomId
     * @param beforeTime
     * @param projectId
     * @param modifyTime
     * @param remark
     */
    private void openOrCreateAlert(String deviceUuid, Long roomId, Date beforeTime, Integer projectId, Date modifyTime, String remark, String type) {
        LogHelper.info(logger, "【告警JOB】打开或新建告警处理，deviceUuid={0},remark={1}", deviceUuid, remark);
        // 公司、项目 关联关系
        CompanyProjectModel companyProject = companyProjectRepository.queryByProjectId(projectId);
        Long companyId = companyProject.getSysCompanyId();
        // 同房号，同类型，关闭时间2天内的告警信息
        List<AlertInfoModel> alertList = this.alertInfoRepository
                .queryAlertInfo(AlertTypeEnum.FAULT.getCode(), roomId, AlertStatusEnum.OFF.getCode(), beforeTime, null, companyId);
        if (CollectionUtils.isNotEmpty(alertList)) {
            for (AlertInfoModel alertInfoModel : alertList) {
                // 打开原告警
                openOriginalAlert(alertInfoModel);
            }
        } else {
            Map<String, String> extInfo = new HashMap<>();
            extInfo.put("device_uuid", deviceUuid);
            // 创建新告警
            List<AlertInfoModel> alertInfoModel = this.alertInfoRepository
                    .queryByRelevanceId(roomId, type);
            if (CollectionUtils.isEmpty(alertInfoModel)) {
                createNewAlert(roomId, null, companyId, projectId, modifyTime, type, null, remark, roomId, null, extInfo);
            }
        }
    }

    /**
     * 电表处理
     *
     * @param nowTime
     * @param jobTime
     * @param beforeTime
     */
    private void eleMeterAlertHandle(Long nowTime, Long jobTime, Date beforeTime) {
        // 查询离线设备
        List<DevElemeterInfoModel> eleMeterList = this.devElemeterRespository.queryOfflineElemeter();
        if (CollectionUtils.isNotEmpty(eleMeterList)) {
            for (DevElemeterInfoModel model : eleMeterList) {
                Date modifyTime = model.getLastModifyTime();
                Long roomId = model.getRoomId();
                Long time = modifyTime.getTime();
                if ((nowTime - time) > Constant.DATE_HOUR_2) {
                    if (((jobTime - Constant.DATE_HOUR_2) < time) && ((nowTime - Constant.DATE_HOUR_2) > time)) {
                        LogHelper.info(logger, "【告警JOB】电表处理，uuid={0},roomId={1}", model.getEleUuid(), model.getRoomId());
                        this.openOrCreateAlert(model.getEleUuid(), roomId, beforeTime, model.getProjectId(), modifyTime, "电表无法读数",
                                AlertTypeEnum.ELE.getCode());
                    }
                }
            }
        }
    }

    /**
     * 水表处理 信息添加
     *
     * @param nowTime
     * @param jobTime
     * @param beforeTime
     */
    private void waterMeterAlertHandle(Long nowTime, Long jobTime, Date beforeTime) {
        List<DevWatermeterInfoModel> waterMeterList = this.devWatermeterRespository.queryOfflineWaterMeter();
        if (CollectionUtils.isNotEmpty(waterMeterList)) {
            for (DevWatermeterInfoModel model : waterMeterList) {
                Date modifyTime = model.getLastModifyTime();
                Long time = modifyTime.getTime();
                if ((nowTime - time) > Constant.DATE_HOUR_2) {
                    if (((jobTime - Constant.DATE_HOUR_2) < time) && ((nowTime - Constant.DATE_HOUR_2) > time)) {
                        LogHelper.info(logger, "【告警JOB】水表处理，uuid={0},roomId={1}", model.getWaterUuid(), model.getRoomId());
                        this.openOrCreateAlert(model.getWaterUuid(), model.getRoomId(), beforeTime, model.getProjectId(), modifyTime, "水表无法读数",
                                AlertTypeEnum.WAT.getCode());
                    }
                }
            }
        }
    }

    /**
     * 门锁告警 信息添加
     *
     * @param nowTime
     * @param jobTime
     * @param beforeTime
     */
    private void doorLockAlertHandle(Long nowTime, Long jobTime, Date beforeTime) {
        List<RoomDoorLockModel> roomDoorLockModels = this.roomDoorLockRepository.queryOfflineDoorlock();
        if (CollectionUtils.isNotEmpty(roomDoorLockModels)) {
            for (RoomDoorLockModel model : roomDoorLockModels) {
                Date modifyTime = model.getLastModifyTime();
                Long time = modifyTime.getTime();
                if ((nowTime - time) > Constant.DATE_HOUR_2) {
                    if (((jobTime - Constant.DATE_HOUR_2) < time) && ((nowTime - Constant.DATE_HOUR_2) > time)) {
                        LogHelper.info(logger, "【告警JOB】门锁处理，uuid={0},roomId={1}", model.getUuid(), model.getRoomId());
                        this.openOrCreateAlert(model.getUuid(), model.getRoomId(), beforeTime, model.getProjectId(), modifyTime, "门锁离线",
                                AlertTypeEnum.DOOR.getCode());
                    }
                }
            }
        }
    }

    /**
     * @description: 打开原告警
     * @param:
     * @return:
     * @author: chenyl
     * @date: 2018/7/5 9:47
     **/
    public void openOriginalAlert(AlertInfoModel alertInfoModel) {
        alertInfoModel.setLastHandleTime(new Date());
        alertInfoModel.setAlertStatus(AlertStatusEnum.WAITING.getCode());
        EvolveInfoModel evolveInfoModel = new EvolveInfoModel();
        evolveInfoModel.setStatus(AlertStatusEnum.WAITING.getCode());
        evolveInfoModel.setAlertId(alertInfoModel.getAlertId());
        evolveInfoModel.setAlertType(alertInfoModel.getAlertType());
        evolveInfoModel.setHandlerId(-1L);
        evolveInfoModel.setSysCompanyId(alertInfoModel.getSysCompanyId());
        evolveInfoModel.setRemark("因报警信息依然存在，故系统自动改变状态为'未处理'");
        pmsDBTransactionTemplate.execute((transactionStatus) -> {
            // 打开原告警
            this.alertInfoRepository.updateAlertInfo(alertInfoModel);
            // 打开原告警时，处理进展操作
            this.evolveInfoRepository.createEvolev(evolveInfoModel);
            return null;
        });
    }

    /**
     * @description: 创建新告警
     * @param:
     * @return:
     * @author: chenyl
     * @date: 2018/7/5 9:47
     **/
    public void createNewAlert(Long roomId, Long leaseId, Long companyId, Integer projectId, Date createTime, String alertType, Date planTime,
                               String remark,
                               Long relevanceId, String leaseNum, Map<String, String> extInfo) {
        AlertInfoModel alertInfoModel = new AlertInfoModel();
        alertInfoModel.setProjectId(projectId);
        alertInfoModel.setSysCompanyId(companyId);
        alertInfoModel.setAlertStatus(AlertStatusEnum.WAITING.getCode());
        alertInfoModel.setAlertType(alertType);
        alertInfoModel.setFirstAlertTime(createTime);
        alertInfoModel.setRoomId(roomId);
        alertInfoModel.setPlanTime(planTime);
        alertInfoModel.setRemark(StringUtils.isNotBlank(remark) ? remark : null);
        alertInfoModel.setRelevanceId(relevanceId);
        alertInfoModel.setLeaseNum(leaseNum);
        alertInfoModel.setLeaseId(leaseId);
        alertInfoModel.setExtInfo(extInfo);
        pmsDBTransactionTemplate.execute((transactionStatus) -> {
            this.alertInfoRepository.createNewAlert(alertInfoModel);
            return null;
        });
    }

    @Override
    public void createNewAlertByUser(Long roomId, Long leaseId, Long companyId, Integer projectId, Date createTime, String alertType, Date planTime,
                                     String remark,
                                     Long relevanceId, String leaseNum, Map<String, String> extInfo) {
        AlertInfoModel alertInfoModel = new AlertInfoModel();
        alertInfoModel.setProjectId(projectId);
        alertInfoModel.setSysCompanyId(companyId);
        alertInfoModel.setAlertStatus(AlertStatusEnum.WAITING.getCode());
        alertInfoModel.setAlertType(alertType);
        alertInfoModel.setFirstAlertTime(createTime);
        alertInfoModel.setRoomId(roomId);
        alertInfoModel.setPlanTime(planTime);
        alertInfoModel.setRemark(StringUtils.isNotBlank(remark) ? remark : null);
        alertInfoModel.setRelevanceId(relevanceId);
        alertInfoModel.setLeaseNum(leaseNum);
        alertInfoModel.setLeaseId(leaseId);
        alertInfoModel.setExtInfo(extInfo);
        alertInfoModel.setCreateId(LocalContextHolder.getContext().getUserId());

        pmsDBTransactionTemplate.execute((transactionStatus) -> {
            this.alertInfoRepository.createNewAlert(alertInfoModel);
            return null;
        });
    }

    /**
     * @description: 工作台告警列表
     * @param:
     * @return:
     * @author: chenyl
     * @date: 2018/7/6 13:56
     **/
    @Override
    public APIResponse listByPage(APIRequest<AlertInfoRequest> request) {
        LogHelper.info(logger, "【工作台 —— 告警列表】,method = {0},请求参数：{1}", "listByPage()", request);
        if (request != null) {
            AlertInfoRequest bizRequest = request.getBizRequest(AlertInfoRequest.class);
            Integer projectId = LocalContextHolder.getContext().getProjectId();
            PageModel pageModel;
            if ((bizRequest != null)) {
                String alertType = bizRequest.getAlertType();
                // 催租提醒
                if (alertType.equals(AlertTypeEnum.DUE.getCode())) {
                    List<Integer> billTypeIdList = this.billTypeRepository.queryBillTypeIdListByCode(BillTypeCodeEnum.RENT.getCode());
                    List<DueAlertDTO> dueAlertDTOList = new ArrayList<>();
                    DueAlertResponse bizResponse = new DueAlertResponse() {{
                        setTotal(0L);
                    }};
                    pageModel = new PageModel(bizRequest.getPageNum(), bizRequest.getPageSize());
                    PageResultModel<AlertInfoModel> pageResultModel = this.alertInfoRepository.queryInfoByType(Arrays.asList(alertType), projectId, pageModel);
                    if (pageResultModel != null) {
                        bizResponse.setTotal(pageResultModel.getTotal());
                        List<AlertInfoModel> alertInfoModelList = pageResultModel.getDatas();
                        if (CollectionUtils.isNotEmpty(alertInfoModelList)) {
                            DueAlertDTO dueAlertDTO;
                            for (AlertInfoModel model : alertInfoModelList) {
                                LeaseInfoModel leaseInfoModel = this.leaseRepository.queryLeaseInfoModel(model.getLeaseId());
                                SysUserModel user = this.userRepository.selectByUserId(model.getLastHandlerId());
                                List<EvolveInfoModel> evolveInfoModels = this.evolveInfoRepository.queryEvolveByAlertId(model.getAlertId());
                                List<EvolveDTO> evolveDTOS = this.evolveListHandle(evolveInfoModels);
                                dueAlertDTO = CachedBeanCopier.copyConvert(model, DueAlertDTO.class);
                                Integer amount = this.leaseRoomBillRepository.queryAmount(model.getLeaseId(), model.getRelevanceId().intValue(),
                                        billTypeIdList.get(0));
                                dueAlertDTO.setContractNum(model.getLeaseNum());
                                dueAlertDTO.setBillAmount(UnitConvertUtil.pointsToYuan(String.valueOf(amount)));
                                dueAlertDTO.setContactName(leaseInfoModel.getContactName());
                                dueAlertDTO.setContactPhone(leaseInfoModel.getContactPhone());
                                dueAlertDTO.setEvolveDTOS(evolveDTOS);
                                if (user != null) {
                                    dueAlertDTO.setLastHandlerName(user.getUserName());
                                }
                                dueAlertDTOList.add(dueAlertDTO);
                            }
                        }
                    }
                    bizResponse.setDueAlertDTOS(dueAlertDTOList);
                    return APIResponse.instance(bizResponse);
                }

                // 应退未退
                if (alertType.equals(AlertTypeEnum.NO_CHECKOUT.getCode())) {
                    List<NoCheckoutAlertDTO> noCheckoutAlertDTOS = new ArrayList<>();
                    NoCheckoutAlertResponse response = new NoCheckoutAlertResponse();
                    pageModel = new PageModel(bizRequest.getPageNum(), bizRequest.getPageSize());
                    PageResultModel<AlertInfoModel> listModel = this.alertInfoRepository.queryInfoByType(Arrays.asList(alertType), projectId, pageModel);
                    response.setTotal(0L);
                    if (listModel != null) {
                        List<AlertInfoModel> alertInfoModelList = listModel.getDatas();
                        response.setTotal(listModel.getTotal());
                        if (CollectionUtils.isNotEmpty(alertInfoModelList)) {
                            NoCheckoutAlertDTO noCheckoutAlertDTO;
                            for (AlertInfoModel model : alertInfoModelList) {
                                LeaseInfoModel leaseInfoModel = this.leaseRepository.queryLeaseInfoModel(model.getLeaseId());
                                SysUserModel user = this.userRepository.selectByUserId(model.getLastHandlerId());
                                List<EvolveInfoModel> evolveInfoModels = this.evolveInfoRepository.queryEvolveByAlertId(model.getAlertId());
                                List<EvolveDTO> evolveDTOS = this.evolveListHandle(evolveInfoModels);
                                noCheckoutAlertDTO = CachedBeanCopier.copyConvert(model, NoCheckoutAlertDTO.class);
                                noCheckoutAlertDTO.setEvolveDTOS(evolveDTOS);
                                noCheckoutAlertDTO.setLeaseId(model.getLeaseId());
                                noCheckoutAlertDTO.setContractNum(model.getLeaseNum());
                                noCheckoutAlertDTO.setContactName(leaseInfoModel.getContactName());
                                noCheckoutAlertDTO.setContactPhone(leaseInfoModel.getContactPhone());
                                if (user != null) {
                                    noCheckoutAlertDTO.setLastHandlerName(user.getUserName());
                                }
                                noCheckoutAlertDTOS.add(noCheckoutAlertDTO);
                            }
                        }
                    }
                    response.setNoCheckoutAlertDTO(noCheckoutAlertDTOS);
                    return APIResponse.instance(response);
                }

                // 设备故障
                if (alertType.equals(AlertTypeEnum.FAULT.getCode())) {
                    List<FaultAlertDTO> faultAlertDTOS = new ArrayList<>();

                    FaultAlertResponse response = new FaultAlertResponse();
                    pageModel = new PageModel(bizRequest.getPageNum(), bizRequest.getPageSize());
                    PageResultModel<AlertInfoModel> listModel =
                            this.alertInfoRepository.queryInfoByType(Arrays.asList(AlertTypeEnum.FAULT.getCode(), AlertTypeEnum.ELE.getCode(),
                                    AlertTypeEnum.WAT.getCode(), AlertTypeEnum.DOOR.getCode()),
                                    projectId,
                                    pageModel);
                    response.setTotal(0L);
                    if (listModel != null) {
                        List<AlertInfoModel> alertInfoModelList = listModel.getDatas();
                        response.setTotal(listModel.getTotal());
                        if (CollectionUtils.isNotEmpty(alertInfoModelList)) {
                            FaultAlertDTO faultAlertDTO;
                            for (AlertInfoModel model : alertInfoModelList) {
                                RoomInfoModel roomInfoModel = this.roomRepository.queryRoomInfoModel(model.getRoomId());
                                SysUserModel user = this.userRepository.selectByUserId(model.getLastHandlerId());
                                List<EvolveInfoModel> evolveInfoModels = this.evolveInfoRepository.queryEvolveByAlertId(model.getAlertId());
                                List<EvolveDTO> evolveDTOS = this.evolveListHandle(evolveInfoModels);
                                faultAlertDTO = CachedBeanCopier.copyConvert(model, FaultAlertDTO.class);
                                if (user != null) {
                                    faultAlertDTO.setLastHandlerName(user.getUserName());
                                }
                                faultAlertDTO.setEvolveDTOS(evolveDTOS);
                                if (roomInfoModel != null) {
                                    faultAlertDTO.setRoomName(roomInfoModel.getRoomName());
                                    faultAlertDTO.setTitle(roomInfoModel.getRoomName() + model.getRemark());
                                }
                                faultAlertDTOS.add(faultAlertDTO);
                            }
                        }
                    }
                    response.setFaultAlertDTOs(faultAlertDTOS);
                    return APIResponse.instance(response);
                }

                // 租约财务复核
                if (alertType.equals(AlertTypeEnum.LFCREVIEW.getCode())) {

                    List<LeaseFCReviewDTO> leaseFCReviewDTOList = new ArrayList<>();

                    LFCReviewResponse response = new LFCReviewResponse();
                    response.setTotal(0L);

                    pageModel = new PageModel(bizRequest.getPageNum(), bizRequest.getPageSize());

                    PageResultModel<AlertInfoModel> listModel = this.alertInfoRepository
                            .queryInfoByType(Arrays.asList(AlertTypeEnum.LFCREVIEW.getCode()), projectId, pageModel);

                    if (listModel != null) {
                        List<AlertInfoModel> alertInfoModelList = listModel.getDatas();
                        response.setTotal(listModel.getTotal());
                        if (CollectionUtils.isNotEmpty(alertInfoModelList)) {

                            LeaseFCReviewDTO leaseFCReviewDTO;

                            for (AlertInfoModel model : alertInfoModelList) {

                                leaseFCReviewDTO = CachedBeanCopier.copyConvert(model, LeaseFCReviewDTO.class);

                                SysUserModel user = this.userRepository.selectByUserId(model.getCreateId());
                                leaseFCReviewDTO.setCreateUserName(user.getUserName());

                                leaseFCReviewDTOList.add(leaseFCReviewDTO);
                            }
                        }
                    }
                    response.setLeaseFCReviewDTOList(leaseFCReviewDTOList);
                    return APIResponse.instance(response);
                }

            }
        }
        return null;
    }

    @Override
    public APIResponse<AddEvolveResponse> addNewEvolve(APIRequest<AddEvolveRequest> request) {
        LogHelper.info(logger, "【处理告警信息  增加进展】,method = {0},请求参数：{1}", "addNewEvolve()", request);
        Long evolveId = 0L;
        Long userId = LocalContextHolder.getContext().getUserId();
        Long sysCompanyId = LocalContextHolder.getContext().getCompanyId();
        AddEvolveResponse response = new AddEvolveResponse();
        if (request != null) {
            AddEvolveRequest addEvolveRequest = request.getBizRequest();
            if (addEvolveRequest != null) {
                EvolveInfoModel evolveInfoModel = new EvolveInfoModel();
                evolveInfoModel.setRemark(addEvolveRequest.getEvolveRemark());
                evolveInfoModel.setAlertId(addEvolveRequest.getAlertId());
                evolveInfoModel.setStatus(addEvolveRequest.getEvolveStatus());
                evolveInfoModel.setAlertType(addEvolveRequest.getAlertType());
                evolveInfoModel.setHandlerId(userId);
                evolveInfoModel.setSysCompanyId(sysCompanyId);
                evolveId = pmsDBTransactionTemplate.execute((transactionStatus) -> {

                    Long id = this.evolveInfoRepository.createEvolev(evolveInfoModel);
                    this.alertInfoRepository
                            .updateAlertHandler(addEvolveRequest.getAlertId(), userId, new Date(), addEvolveRequest.getEvolveStatus());

                    return id;
                });
            }
        }
        response.setEvolveId(evolveId);
        return APIResponse.instance(response);
    }

    private List<EvolveDTO> evolveListHandle(List<EvolveInfoModel> evolveInfoModels) {
        List<EvolveDTO> evolveDTOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(evolveInfoModels)) {
            for (EvolveInfoModel e : evolveInfoModels) {
                EvolveDTO evolveDTO = CachedBeanCopier.copyConvert(e, EvolveDTO.class);
                if (e.getHandlerId() == -1L) {
                    evolveDTO.setHandler("系统");
                } else {
                    SysUserModel handler = this.userRepository.selectByUserId(e.getHandlerId());
                    evolveDTO.setHandler(handler.getUserName());
                }
                evolveDTOS.add(evolveDTO);
            }
        }
        return evolveDTOS;
    }

    /**
     * @description: 告警列表
     * @param:
     * @return:
     * @author: chenyl
     * @date: 2018/7/11 18:58
     **/
    @Override
    public APIResponse<RiskResponse> listRiskInfo(APIRequest<RiskRequest> apiRequest) {
        LogHelper.info(logger, "【报警警信息】,method = {0},请求参数：{1}", "listRiskInfo()", apiRequest);
        RiskResponse response = new RiskResponse();
        Integer projectId = LocalContextHolder.getContext().getProjectId();
        List<RiskInfoDTO> riskInfoDTOS = new ArrayList<>();
        if (apiRequest != null) {
            RiskRequest request = apiRequest.getBizRequest();
            PageModel pageModel;
            if (request != null) {
                pageModel = new PageModel(request.getPageNum(), request.getPageSize());
                String alertType = request.getAlertType();
                PageResultModel<AlertInfoModel> listModel;
                if (("ALL").equalsIgnoreCase(alertType)) {
                    listModel = this.alertInfoRepository.queryAlertInfo(null, request.getAlertStatus(), request.getPriType(), projectId, pageModel);
                } else if (("FAULT").equalsIgnoreCase(alertType)) {
                    listModel = this.alertInfoRepository
                            .queryAlertInfo(Arrays.asList(AlertTypeEnum.FAULT.getCode(), AlertTypeEnum.ELE.getCode(),
                                    AlertTypeEnum.WAT.getCode(), AlertTypeEnum.DOOR.getCode()), request.getAlertStatus(), request.getPriType(), projectId, pageModel);
                } else {
                    listModel = this.alertInfoRepository
                            .queryAlertInfo(Arrays.asList(alertType), request.getAlertStatus(), request.getPriType(), projectId, pageModel);
                }
                response.setTotal(0L);
                if (listModel != null) {
                    response.setTotal(listModel.getTotal());
                    List<AlertInfoModel> alertInfoModels = listModel.getDatas();
                    if (CollectionUtils.isNotEmpty(alertInfoModels)) {
                        RiskInfoDTO riskInfoDTO;
                        for (AlertInfoModel model : alertInfoModels) {

                            RoomInfoModel roomInfoModel = this.roomRepository.queryRoomInfoModel(model.getRoomId());
                            SysUserModel user = this.userRepository.selectByUserId(model.getLastHandlerId());
                            List<EvolveInfoModel> evolveInfoModels = this.evolveInfoRepository.queryEvolveByAlertId(model.getAlertId());
                            List<EvolveDTO> evolveDTOS = this.evolveListHandle(evolveInfoModels);
                            riskInfoDTO = CachedBeanCopier.copyConvert(model, RiskInfoDTO.class);
                            if (AlertTypeEnum.DUE.getCode().equals(model.getAlertType())) {
                                List<Integer> integers = this.billTypeRepository.queryBillTypeIdListByCode(BillTypeCodeEnum.RENT.getCode());
                                Integer amount = this.leaseRoomBillRepository.queryAmount(model.getLeaseId(), model.getRelevanceId().intValue(),
                                        integers.get(0));
                                riskInfoDTO.setBillAmount(UnitConvertUtil.pointsToYuan(String.valueOf(amount)));
                            }
                            if (AlertTypeEnum.DUE.getCode().equals(model.getAlertType()) || AlertTypeEnum.NO_CHECKOUT.getCode().equals(model.getAlertType())) {
                                LeaseInfoModel leaseInfoModel = this.leaseRepository.queryLeaseInfoModel(model.getLeaseId());
                                riskInfoDTO.setContractNum(leaseInfoModel.getContractNum());
                                riskInfoDTO.setContactName(leaseInfoModel.getContactName());
                                riskInfoDTO.setContactPhone(leaseInfoModel.getContactPhone());
                            }
                            if (user != null) {
                                riskInfoDTO.setLastHandlerName(user.getUserName());
                            }
                            riskInfoDTO.setEvolveDTOS(evolveDTOS);
                            if (roomInfoModel != null) {
                                riskInfoDTO.setRoomName(roomInfoModel.getRoomName());
                                riskInfoDTO.setTitle(roomInfoModel.getRoomName() + model.getRemark());
                            }
                            riskInfoDTOS.add(riskInfoDTO);
                        }
                    }
                }
            }
        }
        response.setRiskInfoDTOS(riskInfoDTOS);
        return APIResponse.instance(response);
    }

    @Override
    public APIResponse<DataStatResponse> dataStat(APIRequest<DataStatRequest> request) {
        LogHelper.info(logger, "【房间空置率和入住人数】,method = {0},请求参数：{1}", "dataStat()", request);
        DataStatResponse response = new DataStatResponse();
        Integer projectId = LocalContextHolder.getContext().getProjectId();
        if (request != null) {
            List<Long> roomIds = this.roomRepository.queryIdsBySearch(projectId, null, null, null);
            if (roomIds != null && roomIds.size() > 0) {
                PageResultModel<RoomInfoModel> room = this.roomRepository.searchRoomList(null, null, null,
                        Arrays.asList(projectId), null, null, true, null, null,
                        null, null, null);
                if (room != null) {
                    //总房数
                    Integer roomTotal = room.getDatas().size();
                    //空房数
                    PageResultModel<RoomInfoModel> vacancies = this.roomRepository.searchRoomList(null, null, null,
                            Arrays.asList(projectId), null, SearchRoomStatusEnum.VACANCIES, true, null, null,
                            null, null, null);

                    //空置率
                    Long vacancie = vacancies.getTotal();
                    double vacant = (vacancie.doubleValue() / roomTotal) * 100;
                    vacant = Double.parseDouble(String.format("%.1f", vacant));
                    response.setVacant(vacant);
                }
                //总床数
                Double bedTotal = 0D;
                //入住人数
                Double dataCheckIn = 0D;
                List<List<Long>> roomIdList = splitList(roomIds, 500);
                for (List<Long> rooms : roomIdList) {
                    double bed = this.roomRepository.queryBedTotal(rooms);
                    double bedCheckin = this.renterRepository.queryCheckInTotal(RenterStatusEnum.CHECKIN.getCode(), rooms);
                    bedTotal += bed;
                    dataCheckIn += bedCheckin;
                }
                //入住床数百分百
                double checkInfo = 0D;
                if (bedTotal != 0) {
                    checkInfo = (dataCheckIn / bedTotal) * 100;
                }
                response.setCheckIn(dataCheckIn.intValue());
                response.setCheckInfo((int) checkInfo);
                return APIResponse.instance(response);
            } else {
                response.setVacant(100D);
                response.setCheckIn(0);
                response.setCheckInfo(0);
                return APIResponse.instance(response);
            }
        }
        return null;
    }

    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        List<List<T>> listArray = new ArrayList<List<T>>();
        if (list != null && pageSize > 0) {
            int listSize = list.size();
            if (listSize <= pageSize) {
                listArray.add(list);
                return listArray;
            }
            int batchSize = listSize / pageSize;
            int remain = listSize % pageSize;

            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * pageSize;
                int toIndex = fromIndex + pageSize;
                listArray.add(list.subList(fromIndex, toIndex));
            }
            if (remain > 0) {
                listArray.add(list.subList(listSize - remain, listSize));
            }
        }
        return listArray;
    }


    @Override
    public void alertDueJob() {
        LogHelper.info(logger, "【执行JOB，定时处理欠费告警信息】,method = alertDueJob()");
        List<AlertInfoModel> alertList = alertInfoRepository.queryParam(
                null,
                null,
                null,
                Arrays.asList(AlertTypeEnum.DUE.getCode()),
                Arrays.asList(AlertStatusEnum.IN_HANDLE.getCode(), AlertStatusEnum.WAITING.getCode()),
                null,
                null,
                null,
                null);
        if (CollectionUtils.isNotEmpty(alertList)) {
            List<Integer> billTypeId = billTypeRepository.queryBillTypeIdListByCode(BillTypeCodeEnum.RENT.getCode());
            //账单列表
            List<LeaseRoomBillModel> leaseRoomBillList = leaseRoomBillRepository.billList(null, null, null, LeaseRoomBillBillBizTypeEnum.GATHERING,
                    Arrays.asList(LeaseRoomBillBillStatusEnum.PARTIAL_PAYMENT.getCode(), LeaseRoomBillBillStatusEnum.NO_PAYMENT.getCode()), null, billTypeId, null, new Date());
            pmsDBTransactionTemplate.execute((transactionStatus) -> {
                Map<Long, Map<Integer, List<LeaseRoomBillModel>>> billMap = null;
                if (CollectionUtils.isNotEmpty(leaseRoomBillList)) {
                    billMap = leaseRoomBillList.parallelStream().filter(e -> null != e.getAccountPeriod())
                            .collect(Collectors.groupingBy(LeaseRoomBillModel::getLeaseId,
                                    Collectors.groupingBy(LeaseRoomBillModel::getAccountPeriod)));
                }
                if (MapUtils.isNotEmpty(billMap)) {
                    for (AlertInfoModel alert : alertList) {
                        if (null != alert.getLeaseId() && null != alert.getRelevanceId()) {
                            Map m = billMap.get(alert.getLeaseId());
                            if (null == m || null == m.get(alert.getRelevanceId().intValue())) {
                                // 关闭告警信息
                                this.alertInfoRepository.updateAlertHandler(alert.getAlertId(), 0L, new Date(), AlertStatusEnum.OFF.getCode());
                                // 添加日志
                                this.addAlertLog(
                                        0L,
                                        alert.getAlertType(),
                                        "EDIT",
                                        SmsSendStatusEnum.SUCCESS.getCode(),
                                        "JOB欠费告警处理,告警ID:" + alert.getAlertId(),
                                        alert.getAlertId());
                            }
                        }
                    }
                } else {
                    List<Long> alertIds = alertList.parallelStream().map(AlertInfoModel::getAlertId).collect(Collectors.toList());
                    // 关闭告警信息
                    this.alertInfoRepository.updateBatchAlertHandler(alertIds, 0L, new Date(), AlertStatusEnum.OFF.getCode());
                    // 添加日志
                    this.addAlertLog(
                            0L,
                            AlertTypeEnum.DUE.getCode(),
                            "EDIT",
                            SmsSendStatusEnum.SUCCESS.getCode(),
                            "JOB欠费告警处理,告警ID:" + alertIds.toString(),
                            null);
                }
                return null;
            });
        }
    }

    @Override
    public void alertNoCheckOutJob() {
        LogHelper.info(logger, "【执行JOB，定时处理应退未退房间告警信息】,method = alertNoCheckOutJob()");
        pmsDBTransactionTemplate.execute((transactionStatus) -> {
            // 查出已退未退告警信息
            List<AlertInfoModel> alertList = alertInfoRepository.queryParam(
                    null,
                    null,
                    null,
                    Arrays.asList(AlertTypeEnum.NO_CHECKOUT.getCode()),
                    Arrays.asList(AlertStatusEnum.IN_HANDLE.getCode(), AlertStatusEnum.WAITING.getCode()),
                    null,
                    null,
                    null,
                    null);
            if (CollectionUtils.isNotEmpty(alertList)) {
                List<Long> leaseIds = alertList.parallelStream().map(AlertInfoModel::getLeaseId).collect(Collectors.toList());
                List<LeaseRoomModel> list = null;
                if (CollectionUtils.isNotEmpty(leaseIds)) {
                    list = leaseRepository.queryByLeaseIdsAndStatus(leaseIds, NewLeaseRoomStatusEnum.EFFECTIVE.getCode());
                }
                if (CollectionUtils.isEmpty(list)) {
                    List<Long> alertIds = alertList.parallelStream().map(AlertInfoModel::getAlertId).collect(Collectors.toList());
                    this.alertInfoRepository.updateBatchAlertHandler(alertIds, 0L, new Date(), AlertStatusEnum.OFF.getCode());
                    // 添加日志
                    this.addAlertLog(
                            0L,
                            AlertTypeEnum.NO_CHECKOUT.getCode(),
                            "EDIT",
                            SmsSendStatusEnum.SUCCESS.getCode(),
                            "JOB应退未退房间告警处理,告警ID:" + alertIds.toString(),
                            null);
                } else {
                    leaseIds = list.parallelStream().map(LeaseRoomModel::getLeaseId).distinct().collect(Collectors.toList());
                    for (AlertInfoModel alert : alertList) {
                        if (null != alert.getLeaseId()) {
                            if (!leaseIds.contains(alert.getLeaseId())) {
                                this.alertInfoRepository.updateAlertHandler(alert.getAlertId(), 0L, new Date(), AlertStatusEnum.OFF.getCode());
                                // 添加日志
                                this.addAlertLog(
                                        0L,
                                        alert.getAlertType(),
                                        "EDIT",
                                        SmsSendStatusEnum.SUCCESS.getCode(),
                                        "JOB应退未退房间告警处理,告警ID:" + alert.getAlertId(),
                                        alert.getAlertId());
                            }
                        }
                    }
                }
            }
            return null;
        });
    }

    /**
     * @param operationId
     * @param logType
     * @param operationType
     * @param operationContent
     * @param alertId
     * @Description : 添加告警日志
     * @Author : zsy
     * @Date : 2018年9月26日 下午6:10:06
     */
    public void addAlertLog(Long operationId, String logType, String operationType, String operationStatus, String operationContent, Long alertId) {
        AlertInfoLogModel alertLogModel = new AlertInfoLogModel();
        alertLogModel.setOperationId(operationId);
        alertLogModel.setLogType(logType);
        alertLogModel.setOperationType(operationType);
        alertLogModel.setOperationStatus(operationStatus);
        alertLogModel.setOperationContent(operationContent);
        alertLogModel.setAlertId(alertId);
        this.alertLogRepository.createAlertLog(alertLogModel);
    }

    @Autowired
    private BillTypeRepository billTypeRepositoryImpl;

    @Override
    public void messageAlertJob() {
        LogHelper.debug(logger, "【提醒消息】【账单消息生成】");
        List<String> billStatus = Arrays
                .asList(LeaseRoomBillBillStatusEnum.NO_PAYMENT.getCode(), LeaseRoomBillBillStatusEnum.PARTIAL_PAYMENT.getCode());
        List<LeaseRoomBillModel> leaseRoomBillModels = this.leaseRoomBillRepository.queryAllInfo(billStatus, CommonStatusEnum.EFFECTIVE.getCode());
        if (CollectionUtils.isNotEmpty(leaseRoomBillModels)) {
            List<BillTypeModel> billTypeList = this.billTypeRepositoryImpl.queryBillType(BillTypeEnum.FANGFEI.getCode(), BillTypeCodeEnum.RENT.getCode());
            Integer fangfeiTypeId = null;
            if (CollectionUtils.isNotEmpty(billTypeList)) {
                fangfeiTypeId = billTypeList.get(0).getBillTypeId();
            }
            for (LeaseRoomBillModel leaseRoomBillModel : leaseRoomBillModels) {
                Long leaseId = leaseRoomBillModel.getLeaseId();
                Integer billTypeId = leaseRoomBillModel.getBillTypeId();
                Integer billAmount = leaseRoomBillModel.getBillAmount();
                BillTypeModel billTypeModel = this.billTypeRepository.queryBillTypeById(billTypeId);
                String billTypeName = billTypeModel.getBillTypeName();
                // 如果是房费
                if (fangfeiTypeId.equals(billTypeId)) {
                    // 查询租约
                    LeaseInfoModel leaseBaseModel = this.leaseRepository.queryDueInfo(leaseId, NewLeaseStepEnum.LEASE.getCode());
                    if (leaseBaseModel != null) {
                        // 获取租约提醒天数
                        Integer remindTime = leaseBaseModel.getRemindValue();
                        // 计算时差
                        long nowTime = new Date().getTime();
                        Date planPayTime = leaseRoomBillModel.getPlanPayTime();
                        long planTime = planPayTime.getTime();
                        long diffTime = planTime - nowTime;
                        long time = diffTime / (1000 * 60 * 60 * 24);
                        // 当前距离的时间 小于 租约提醒时间
                        if (time <= remindTime) {
                            this.sendMessageAlert(leaseRoomBillModel.getBillId(), leaseRoomBillModel.getLeaseRoomId(),
                                    leaseId, leaseRoomBillModel.getRoomId(), billAmount, billTypeName);
                        }
                    }
                } else {
                    this.sendMessageAlert(leaseRoomBillModel.getBillId(), leaseRoomBillModel.getLeaseRoomId(),
                            leaseId, leaseRoomBillModel.getRoomId(), billAmount, billTypeName);
                }
            }
        }
    }

    @Resource
    private ClientMessageRepository clientMessageRepository;

    /**
     * 新消息提醒
     *
     * @param leaseRoomBillId
     * @param leaseId
     * @param billAmount
     * @param billType
     */
    private void sendMessageAlert(Long leaseRoomBillId, Long leaseRoomId, Long leaseId, Long roomId, Integer billAmount, String billType) {
        LeaseRoomModel leaseRoom = this.leaseRepository.selectOneLeaseRoomConditionWithNoCompany(leaseId, roomId, NewLeaseRoomStatusEnum.EFFECTIVE.getCode());
        if (null != leaseRoom) {
            try {
                List<String> statusList = Arrays.asList(RenterStatusEnum.CHECKIN.getCode());
                pmsDBTransactionTemplate.execute(transactionStatus -> {
                    // 所有在住租客
                    List<LeaseRenterModel> renterList = this.renterRepositoryImpl.queryRenterListByRoomIdAndStatus(leaseRoom.getRoomId(), leaseRoom.getLeaseId(), statusList);
                    if (CollectionUtils.isNotEmpty(renterList)) {
                        for (LeaseRenterModel leaseRenter : renterList) {
                            List<ClientUserRelatedInfoModel> relateList = this.clientUserInfoRepositoryImpl.queryUserRelatedListEqual(
                                    leaseRenter.getInfoId(), UserRelatedTypeEnum.LEASE_RENTER_ID.getCode());
                            if (CollectionUtils.isNotEmpty(relateList)) {
                                ClientMessageModel clientMessage = this.clientMessageRepository.findClientMessage(leaseRoomBillId, ClientMessageType.LEASE_BILL.getCode(), null);
                                if (null == clientMessage) {
                                    ClientUserRelatedInfoModel relatedModel = relateList.get(0);
                                    Map<String, String> map = new HashMap<>();
                                    map.put("billAmount", billAmount != null ? UnitConvertUtil.pointsToYuan(billAmount.toString()) : "0");
                                    map.put("billType", billType);
                                    this.smartMessageServiceImpl.sendClientMessageCommon(
                                            null, leaseRoomBillId, ClientMessageType.LEASE_BILL, JSONHelper.toJson(map), relatedModel.getUserId());
                                }
                            }
                        }
                    }
                    return null;
                });
            } catch (Exception ex) {
                LogHelper.exception(ex, logger, "【新消息提醒】账单提醒异常");
            }
        }
    }

    @Override
    public void clearDoorLockPswJob() {
        LogHelper.info(logger, "【每天中午12:00清除到期房间门锁密码JOB】，AlertServiceImpl.clearDoorLockPswJob()");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        //前一天到期的租约
        List<LeaseInfoModel> baseModels = this.leaseRepository.queryByDate(date);
        if (CollectionUtils.isNotEmpty(baseModels)) {
            for (LeaseInfoModel leaseBaseModel : baseModels) {
                Long leaseId = leaseBaseModel.getLeaseId();
                List<LeaseRoomModel> leaseRoomModels = this.leaseRepository.queryByLeaseIds(leaseId);
                if (CollectionUtils.isNotEmpty(leaseRoomModels)) {
                    for (LeaseRoomModel leaseRoomModel : leaseRoomModels) {
                        String statu = leaseRoomModel.getLeaseRoomStatus();
                        if (!statu.equals(NewLeaseRoomStatusEnum.CHECKOUT.getCode())) {
                            Long roomId = leaseRoomModel.getRoomId();
                            DevInstallModel devInstallModel = this.deviceRepository.queryRoomLock(BusinessTypeEnum.CENTER, roomId, DevTypeEnum.room_lock.getCode());
                            if (devInstallModel != null) {
                                try {
                                    this.passwordService.deletePasswordByRoomId(roomId);
                                } catch (Exception e) {
                                    LogHelper.exception(e, logger, "【JOB】【每天中午12:00清除到期未办理退房门锁密码】发生异常!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
