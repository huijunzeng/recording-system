package com.zjh.recording.system.common.swagger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "swagger")
@Profile({"dev"})
public class SwaggerProperties {

    @ApiModelProperty("创建人信息  可用于定位该模块的负责人")
    private Contact contact = new Contact();

    @ApiModelProperty("标题")
    private String title = "api接口";

    @ApiModelProperty("描述")
    private String description = "api接口文档";

    @ApiModelProperty("版本号")
    private String version = "1.0";

    @Data
    public class Contact {

        @ApiModelProperty("创建人名字")
        private String name = "zjh";

        @ApiModelProperty("创建人邮箱")
        private String email = "";

        @ApiModelProperty("创建人项目地址")
        private String url = "";
    }

}
