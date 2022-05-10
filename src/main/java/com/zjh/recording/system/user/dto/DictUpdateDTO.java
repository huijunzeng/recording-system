package com.zjh.recording.system.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "字典数据更新")
@Data
public class DictUpdateDTO {

    @ApiModelProperty("id")
    private Long id;

    @TableField("字典名称")
    private String name;

    @TableField("字典编码")
    private Long code;

    @TableField("字典值")
    private String value;

    @TableField("备注")
    private String remark;
}
