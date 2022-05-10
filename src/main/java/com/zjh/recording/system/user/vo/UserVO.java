package com.zjh.recording.system.user.vo;

import com.zjh.recording.system.common.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "用户vo实体类")
@Data
public class UserVO extends BaseVO {

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

}
