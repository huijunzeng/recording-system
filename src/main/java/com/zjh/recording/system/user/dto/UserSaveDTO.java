package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户新增")
@Data
public class UserSaveDTO {

    //@NotEmpty :不能为null，且Size>0
    //@NotNull:不能为null，但可以为empty,没有Size的约束
    //@NotBlank:只用于String,不能为null且trim()之后size>0   不能用于包装类型如Long
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty("密码密文")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty("性别（ F 女 M 男 O 其他）")
    private String sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("状态 0正常 1锁定")
    private Integer status;

}
