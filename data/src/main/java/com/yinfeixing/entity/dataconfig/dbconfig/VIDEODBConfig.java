/*
 * VIDEODBConfig.java 1.0.0 2018/2/27  下午3:35
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/2/27  下午3:35 created by yinqiang
 */
package com.yinfeixing.entity.dataconfig.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.yinfeixing.entity.dataconfig.Config;
import com.yinfeixing.entity.dataconfig.annotation.VIDEODB;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {Config.VIDEODB_PACKAGE}, annotationClass = VIDEODB.class, sqlSessionFactoryRef = "videodbSqlSessionFactory")
public class VIDEODBConfig {

    static final String PMSMAPPER_LOCATION = "classpath*:videodb/*/*.xml";

    @Bean(name = "videodbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "pmsdb.datasource")
    public DataSource pmsdbDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "videodbTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(pmsdbDataSource());
    }

    @Bean(name = "videodbTransactionTemplate")
    @Primary
    public TransactionTemplate pmsDBTransactionTemplate() {
        return new TransactionTemplate(masterTransactionManager());
    }

    @Bean(name = "videodbSqlSessionFactory")
    @Primary
    public SqlSessionFactory pmsdbSqlSessionFactory(@Qualifier("videodbDataSource") DataSource pmsdbDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pmsdbDataSource);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", Boolean.FALSE.toString());
        props.setProperty("pageSizeZero", Boolean.TRUE.toString());
        props.setProperty("supportMethodsArguments", Boolean.TRUE.toString());
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PMSMAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
