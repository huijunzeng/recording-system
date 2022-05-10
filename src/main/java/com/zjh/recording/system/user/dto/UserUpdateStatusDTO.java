package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户状态更新表单")
@Data
public class UserUpdateStatusDTO {

    @ApiModelProperty("状态 0正常 1锁定")
    private Integer status;

    @ApiModelProperty("用户id集合")
    @NotEmpty(message = "请选择数据")
    private List<Long> ids;
}
