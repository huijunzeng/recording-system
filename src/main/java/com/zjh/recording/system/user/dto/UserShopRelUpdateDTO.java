package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户店铺关联更新")
@Data
public class UserShopRelUpdateDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("店铺id")
    private Long shopId;
}
