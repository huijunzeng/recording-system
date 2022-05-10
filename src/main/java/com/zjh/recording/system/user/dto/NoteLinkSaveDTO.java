package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "笔记链接新增")
@Data
public class NoteLinkSaveDTO {

    @ApiModelProperty("交易记录id")
    private Long transactionRecordId;

    @ApiModelProperty("笔记链接url")
    private String url;

    @ApiModelProperty("点赞数")
    private Integer likes;

    @ApiModelProperty("平台 1小红书")
    private Integer platform;

    @ApiModelProperty("备注")
    private String remark;
}
