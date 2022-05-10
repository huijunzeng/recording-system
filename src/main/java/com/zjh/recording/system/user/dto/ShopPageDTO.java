package com.zjh.recording.system.user.dto;

import com.zjh.recording.system.common.dto.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "店铺分页列表查询")
@Data
public class ShopPageDTO extends BaseQueryForm {

    @ApiModelProperty("店铺名")
    private String name;

    @ApiModelProperty("店铺url地址")
    private String url;
}
