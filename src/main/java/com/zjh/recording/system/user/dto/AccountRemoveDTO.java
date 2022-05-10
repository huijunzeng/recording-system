package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel(value = "账号信息删除dto")
@Data
public class AccountRemoveDTO {

    @ApiModelProperty("ids")
    @NotEmpty(message = "请选择数据")
    private List<Long> ids;
}
