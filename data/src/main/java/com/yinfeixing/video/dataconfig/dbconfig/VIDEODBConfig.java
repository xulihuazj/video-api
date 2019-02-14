package com.yinfeixing.video.dataconfig.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.yinfeixing.video.dataconfig.Config;
import com.yinfeixing.video.dataconfig.annotation.VIDEODB;
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
@MapperScan(basePackages = {Config.VIDEODB_PACKAGE}, annotationClass = VIDEODB.class, sqlSessionFactoryRef = "pmsdbSqlSessionFactory")
public class VIDEODBConfig {

    static final String PMSMAPPER_LOCATION = "classpath*:video/*/*.xml";

    @Bean(name = "pmsdbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "videodb.datasource")
    public DataSource pmsdbDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "pmsdbTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(pmsdbDataSource());
    }

    @Bean(name = "pmsDBTransactionTemplate")
    @Primary
    public TransactionTemplate pmsDBTransactionTemplate() {
        return new TransactionTemplate(masterTransactionManager());
    }

    @Bean(name = "pmsdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory pmsdbSqlSessionFactory(@Qualifier("pmsdbDataSource") DataSource pmsdbDataSource)
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
