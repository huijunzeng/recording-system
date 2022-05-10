package com.zjh.recording.system.common.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zjh.recording.system.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "查询基础类")
@Slf4j
public class BaseParam<T extends BaseEntity> implements Serializable {

    @ApiModelProperty("查询开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeStart;

    @ApiModelProperty("查询结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeEnd;

    // 封装时间查询包装器方法，避免重复代码
    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(null != this.createdTimeStart, T:: getCreatedTime, this.createdTimeStart)
                .le(null != this.createdTimeEnd, T:: getCreatedTime, this.createdTimeEnd);
        log.info("QueryWrapper : " + queryWrapper.toString());
        return queryWrapper;
    }
}
