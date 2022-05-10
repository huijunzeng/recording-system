package com.zjh.recording.system.user.dto;

import com.zjh.recording.system.common.dto.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户店铺关联分页查询")
@Data
public class UserShopRelPageDTO extends BaseQueryForm {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("店铺id")
    private Long shopId;
}
