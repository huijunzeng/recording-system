package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户更新表单")
@Data
public class UserUpdateDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty("性别（ F 女 M 男 O 其他）")
    private String sex;

    @ApiModelProperty("状态 0正常 1锁定")
    private Integer status;;

}
