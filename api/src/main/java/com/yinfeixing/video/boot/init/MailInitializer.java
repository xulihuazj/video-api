package com.yinfeixing.video.boot.init;


import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.mail.SendMailUtil;
import com.yinfeixing.video.boot.config.MailConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 邮箱服务器初始化
 *
 * @author xulh
 * @Description
 * @Date 2017年7月11日下午4:00:42
 */
@Configuration
public class MailInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LogManager.getLogger(MailInitializer.class);

    @Autowired
    private MailConfig mailConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                Long startTime = System.currentTimeMillis();
                LogHelper.info(logger, "开始异步初始化邮件发送服务，开始时间为：{0}", startTime);
                SendMailUtil.init(mailConfig.getMailUserName(), mailConfig.getMailPassword(), mailConfig.getMailProxyHost(), mailConfig.getMailPort() + "");
                LogHelper.info(logger, "结束异步初始化邮件发送服务，结束时间为：{0}，总用时为：{1}", System.currentTimeMillis(), System.currentTimeMillis() - startTime);
                return "SUCCESS";
            } catch (Exception e) {
                LogHelper.exception(e, logger, "初始化邮件发送服务失败");
                return null;
            } finally {
                executor.shutdown();
            }
        });
        executor.execute(futureTask);
    }

}
