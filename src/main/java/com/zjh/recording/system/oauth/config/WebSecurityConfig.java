package com.zjh.recording.system.oauth.config;

import com.zjh.recording.system.oauth.oauth2.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security配置  HttpSecurity默认先走ResourceServerConfig
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.security.oauth2.permit-urls:/oauth/**, /actuator/**,/*.html,/favicon.ico,/**/*.html,/**/*.css,/**/*.js,/webjars/springfox-swagger-ui/images/**,/swagger-resources/configuration/*,/swagger-resources,/v3/api-docs,/doc.html}")
    private String PERMIT_URLS;

    /**
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 认证对象管理构造器  用于管理用户
     *
     * @param builder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder builder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        //daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        builder.authenticationProvider(daoAuthenticationProvider);
    }

    /**
     *  HTTP请求安全处理  对请求信息的设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] antMatchers = PERMIT_URLS.split(",");
        http.authorizeRequests()
                //允许对于网站静态资源以及某些请求路径的无授权访问（比如swagger）
                .antMatchers(antMatchers).permitAll()
                //设置拦截规则
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .csrf().disable()
                .httpBasic();
    }

    /**
     * Web层面的配置
     */
    @Override
    public void configure(WebSecurity web) {
        /*super.configure(web);*/
        web.ignoring().antMatchers("/favor.ioc");
    }
}
