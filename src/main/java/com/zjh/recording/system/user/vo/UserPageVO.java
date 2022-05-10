package com.zjh.recording.system.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "用户vo实体类")
@Data
public class UserPageVO {

    @ApiModelProperty("id主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("性别（ F 女 M 男 O 其他）")
    private String sex;

    @ApiModelProperty("状态 0正常 1锁定")
    private Integer status;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户角色名称集合")
    private List<String> roleNames;

    @ApiModelProperty("创建人名字")
    private String createdUsername;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}
