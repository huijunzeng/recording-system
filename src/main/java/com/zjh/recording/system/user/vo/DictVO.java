package com.zjh.recording.system.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "字典数据vo实体")
@Data
public class DictVO {

    @ApiModelProperty("id主键")
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
