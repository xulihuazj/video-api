package com.yinfeixing.video.boot.config;

//@Configuration
//@EnableJpaRepositories("com.yinfeixing.video.core.jpa")
//@EnableTransactionManagement
//@ComponentScan
public class VideoJpaConfig {
//
//    @Autowired
//    private DataSource pmsdbDataSource;
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        //vendorAdapter.setShowSql(true);
//        //vendorAdapter.setGenerateDdl(true);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.yinfeixing.video.bean");
//        factory.setDataSource(pmsdbDataSource);
//        Map<String, Object> jpaProperties = new HashMap<>();
//        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//        jpaProperties.put("hibernate.jdbc.batch_size",50);
//        //jpaProperties.put("hibernate.show_sql",true);
//        factory.setJpaPropertyMap(jpaProperties);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
}