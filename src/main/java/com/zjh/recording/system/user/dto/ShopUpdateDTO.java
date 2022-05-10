package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "店铺新增")
@Data
public class ShopUpdateDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("店铺名")
    private String name;

    @ApiModelProperty("店铺url地址")
    private String url;

    @ApiModelProperty("备注")
    private String remark;
}
