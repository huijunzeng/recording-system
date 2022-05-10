package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zjh
 * @Description
 * @date 2021/01/15 13:42
 */
@ApiModel(value = "用户密码重置")
@Data
public class UserResetPasswordDTO {

	@ApiModelProperty("用户id")
	private Long userId;

	@ApiModelProperty("旧密码密文")
	@NotBlank(message = "用户密码不能为空")
	private String oldPassword;

	@ApiModelProperty("新密码密文")
	@NotBlank(message = "用户密码不能为空")
	private String newPassword;
}
