/*
 * TestJobInitializer.java 1.0.0 2018/01/23  21:28
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  21:28 created by xulihua
 */
package com.yinfeixing.video.boot.init;

import com.cf.pms.enums.intelligent.WaterElectricTypeEnum;
import com.cf.pms.service.intelligent.WaterEletricAsyncService;
import com.cf.pms.service.prepay.PrepayService;
import com.cf.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用于测试环境自动执行 Job 调用
 *
 * @author xulihua
 * @description
 * @date 2018/01/23 21:08
 */

@Component
public class TestJobInitializer {

    private final Logger logger = LogManager.getLogger(TestJobInitializer.class);

    @Value("${job.execute.environment}")
    private String environment;

    @Resource
    private PrepayService prepayService;

    /**
     * 预付余额不足30，次日9点发送短信告知入住人
     * 每天9点执行
     */
//    @Scheduled(cron = "0 0 9 * * ?")
//    @Scheduled(cron = "0 0 0/2 * * ?")
    private void sysPrepayBalanceJudeJob() {
        LogHelper.info(logger, "################## JOB:预付余额不足30，次日9点发送短信告知入住人 ##################");
        this.prepayService.sysPrepayBalanceJudeJob();

    }


}
