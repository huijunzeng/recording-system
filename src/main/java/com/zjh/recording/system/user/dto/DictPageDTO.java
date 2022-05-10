package com.zjh.recording.system.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zjh.recording.system.common.dto.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "字典数据分表查询")
@Data
public class DictPageDTO extends BaseQueryForm {

    @TableField("字典名称")
    private String name;

    @TableField("字典编码")
    private Long code;

    @TableField("字典值")
    private String value;
}
