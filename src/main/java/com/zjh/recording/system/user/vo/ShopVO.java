package com.zjh.recording.system.user.vo;

import com.zjh.recording.system.common.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "店铺vo实体类")
@Data
public class ShopVO extends BaseVO {

    @ApiModelProperty("店铺名")
    private String name;

    @ApiModelProperty("店铺url地址")
    private String url;
}
