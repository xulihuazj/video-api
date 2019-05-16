package com.yinfeixing.video.dataconfig.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.orm.jpa.JpaTransactionManager;
//import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class TransactionManagersConfig {

//    @Resource
//    EntityManagerFactory entityManagerFactory;
//
//    @Resource
//    private DataSource pmsdbDataSource;
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager tm =
//                new JpaTransactionManager();
//        tm.setEntityManagerFactory(entityManagerFactory);
//        tm.setDataSource(pmsdbDataSource);
//        return tm;
//    }
}