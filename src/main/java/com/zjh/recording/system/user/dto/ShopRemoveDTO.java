package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel(value = "店铺删除")
@Data
public class ShopRemoveDTO {

    @ApiModelProperty("ids")
    @NotEmpty(message = "请选择数据")
    private List<Long> ids;
}
