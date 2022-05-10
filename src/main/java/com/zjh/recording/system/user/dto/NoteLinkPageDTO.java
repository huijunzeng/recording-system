package com.zjh.recording.system.user.dto;

import com.zjh.recording.system.common.dto.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "笔记链接查询表单")
@Data
public class NoteLinkPageDTO extends BaseQueryForm {

    @ApiModelProperty("交易记录id")
    private Long transactionRecordId;

    @ApiModelProperty("笔记链接url")
    private String url;

    @ApiModelProperty("平台 1小红书")
    private Integer platform;
}
