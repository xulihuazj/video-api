package com.yinfeixing.video;

import com.yinfeixing.video.system.SystemType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages ="com.yinfeixing.video")
@EnableScheduling
@EnableAsync
@EnableJpaAuditing
//@EntityScan(basePackages = "com.yinfeixing.video.core")
public class ApiApplication {

    @GetMapping(value = "/index")
    @SystemType
    public String index() {
        return "Hello,videodb api!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Configuration
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 取消（默认启动security 需要验证权限）
            http.authorizeRequests()
                    .antMatchers("/**").permitAll().anyRequest().fullyAuthenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .failureUrl("/login?error").permitAll()
                    .and()
                    .logout().permitAll()
                    .and()
                    .headers().frameOptions().disable();
            // 取消（默认启动csrf 不允许POST PUT DELETE）
            http.csrf().disable();
        }

    }

}
