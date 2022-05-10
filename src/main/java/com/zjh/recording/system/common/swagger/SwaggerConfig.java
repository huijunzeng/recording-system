package com.zjh.recording.system.common.swagger;

import com.fasterxml.classmate.GenericType;
import com.fasterxml.classmate.TypeResolver;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * swagger接口文档配置
 * @Author: ZJH
 * @Date: 2019/12/5 10:15
 */

@Slf4j
@Configuration
@EnableOpenApi
@EnableKnife4j //集成Knife4j
@EnableConfigurationProperties(value = {SwaggerProperties.class})
@Profile({"dev"}) //只在dev环境生效 与@ConditionalOnProperty效果类似
public class SwaggerConfig {
    // swagger接口界面访问路径 ：http://localhost:9800/swagger-ui/index.html  IP为机器的IP，端口号为工程的端口
    // Knife4j接口界面访问路径 ：http://localhost:9800/doc.html  IP为机器的IP，端口号为工程的端口

    @Autowired
    private TypeResolver typeResolver;

    // 需要使用构造注入
    private SwaggerProperties swaggerProperties;
    @Autowired
    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket api() {
        log.info("swaggerProperties======================: {}", swaggerProperties != null ? swaggerProperties.toString() : null);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // api接口路径，即controller层路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径（除了被@ApiIgnore指定的请求）
                .paths(PathSelectors.any())
                .build()
                .alternateTypeRules(
                        newRule(
                                typeResolver.resolve(
                                        DeferredResult.class,
                                        typeResolver.resolve(GenericType.class, WildcardType.class)
                                ),
                                typeResolver.resolve(WildcardType.class)
                        )
                )
                // 支持的协议
                .protocols(newHashSet("https", "http"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .contact(swaggerProperties.getContact() != null ? new Contact(
                        Optional.ofNullable(swaggerProperties.getContact().getName()).orElse(""),
                        Optional.ofNullable(swaggerProperties.getContact().getUrl()).orElse(""),
                        Optional.ofNullable(swaggerProperties.getContact().getEmail()).orElse("")) : null)
                .build();
    }

    /**
     * 设置授权信息
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        // 在请求头header添加一个名为Authorization的token
        return Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, "token", "header"));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(new SecurityReference("Authorization",
                                        new AuthorizationScope[]{new AuthorizationScope("global", "")}
                                )))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}
