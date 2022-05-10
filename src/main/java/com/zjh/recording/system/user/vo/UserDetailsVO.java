package com.zjh.recording.system.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zjh
 * @Description
 * @date 2021/01/15 16:00
 */
@ApiModel(value = "spring oauth2用户详情vo")
@Data
public class UserDetailsVO {

	@ApiModelProperty("用户id")
	private Long id;

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("密码密文")
	private String password;

	@ApiModelProperty("状态 0正常 1锁定")
	private Integer status;

	@ApiModelProperty("用户角色id集合")
	private List<Long> roleIds;
}
