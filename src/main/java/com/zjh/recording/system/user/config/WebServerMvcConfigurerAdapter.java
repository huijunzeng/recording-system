package com.zjh.recording.system.user.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自定义web配置
 * @Author: ZJH
 * @Date: 2019/12/5 10:15
 */

@Slf4j
@Configuration
public class WebServerMvcConfigurerAdapter implements WebMvcConfigurer {



    /**
     * 排除拦截的路径（swagger）
     */
    @Value("${spring.security.oauth2.permit-urls:/oauth/**, /actuator/**,/*.html,/favicon.ico,/**/*.html,/**/*.css,/**/*.js,/webjars/springfox-swagger-ui/images/**,/swagger-resources/configuration/*,/swagger-resources,/v3/api-docs,/doc.html}")
    private String PERMIT_URLS;

    /**
     * 用户信息拦截器
     * @return
     */
    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    /**
     * 注入拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).excludePathPatterns(PERMIT_URLS.split(","));
    }
}
