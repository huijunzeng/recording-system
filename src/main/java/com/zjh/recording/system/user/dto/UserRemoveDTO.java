package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description:
 * @Author: ZJH
 * @Date: 2021/2/23 0:26
 */
@ApiModel(value = "用户删除dto")
@Data
public class UserRemoveDTO {

    @ApiModelProperty("ids")
    @NotEmpty(message = "请选择数据")
    private List<Long> ids;
}
