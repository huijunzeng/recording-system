package com.zjh.recording.system.oauth.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "recording-system";

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomAuthExceptionHandler customAuthExceptionHandler;

    @Value("${spring.security.oauth2.permit-urls:/oauth/**, /actuator/**,/*.html,/favicon.ico,/**/*.html,/**/*.css,/**/*.js,/webjars/springfox-swagger-ui/images/**,/swagger-resources/configuration/*,/swagger-resources,/v3/api-docs,/doc.html}")
    private String PERMIT_URLS;

    /**
     *  与http安全配置相关 可配置拦截什么URL、设置什么权限等安全控制
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] antMatchers = PERMIT_URLS.split(",");
        http.csrf().disable();
        http.authorizeRequests()
                //允许对于网站静态资源以及某些请求路径的无授权访问（比如swagger）
                .antMatchers(antMatchers).permitAll()
                //设置拦截规则
                .anyRequest().authenticated();
        //添加token续签过滤器（请求refresh token接口）
        //http.addFilterBefore()
    }

    /**
     * 与资源安全配置相关
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(DEMO_RESOURCE_ID)
                .tokenStore(tokenStore())
                .accessDeniedHandler(customAuthExceptionHandler);
                //.authenticationEntryPoint(customAuthExceptionHandler);
    }

    /**
     * tokenStore实现的保存方式
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


}
