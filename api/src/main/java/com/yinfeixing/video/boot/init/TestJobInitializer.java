package com.yinfeixing.video.boot.init;

import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.video.service.app.user.ClientUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用于测试环境自动执行 Job 调用
 *
 * @author xulh
 * @description
 * @date 2018/01/23 21:08
 */

@Component
public class TestJobInitializer {

    private final Logger logger = LogManager.getLogger(TestJobInitializer.class);

    @Resource
    private ClientUserService clientUserServiceImpl;

    /**
     * 预付余额不足30，次日9点发送短信告知入住人
     * 每天9点执行
     */
//    @Scheduled(cron = "0 0 9 * * ?")
//    @Scheduled(cron = "0 0 0/2 * * ?")
    private void sysPrepayBalanceJudeJob() {
        LogHelper.info(logger, "################## JOB:预付余额不足30，次日9点发送短信告知入住人 ##################");

    }


}
