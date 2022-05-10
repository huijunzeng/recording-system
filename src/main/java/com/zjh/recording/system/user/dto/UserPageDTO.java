package com.zjh.recording.system.user.dto;

import com.zjh.recording.system.common.dto.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户分页查询")
@Data
public class UserPageDTO extends BaseQueryForm {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("性别（ F 女 M 男 O 其他）")
    private String sex;

    @ApiModelProperty("状态 0正常 1锁定")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createdUsername;

}
