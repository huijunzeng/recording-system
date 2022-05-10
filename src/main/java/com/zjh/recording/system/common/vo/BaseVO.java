package com.zjh.recording.system.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "vo基础类")
public class BaseVO implements Serializable {

    @ApiModelProperty("id主键")
    private Long id;

    @ApiModelProperty("创建人id")
    @JsonIgnore
    private Long createdBy;

    @ApiModelProperty("创建人名字")
    private String createUserName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("版本号")
    private Long version;
}
