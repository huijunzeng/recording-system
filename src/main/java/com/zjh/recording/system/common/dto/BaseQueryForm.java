package com.zjh.recording.system.common.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjh.recording.system.common.entity.BaseEntity;
import com.zjh.recording.system.common.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2019/8/8 19:08
 */

@ApiModel(value = "基础查询表单")
@Data
@Slf4j
public class BaseQueryForm<T extends BaseEntity> extends PageQueryParam implements Serializable {

    @ApiModelProperty("查询开始时间 yyyy-MM-dd")
    private String createdTimeStart;

    @ApiModelProperty("查询结束时间 yyyy-MM-dd")
    private String createdTimeEnd;

    public LambdaQueryWrapper<T> build() {
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(this.createdTimeStart)) {
            queryWrapper.ge(T:: getCreatedTime, DateUtil.formatDate(this.createdTimeStart + " 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS));
        }
        if (StringUtils.isNotBlank(this.createdTimeEnd)) {
            queryWrapper.le(T:: getCreatedTime, DateUtil.formatDate(this.createdTimeEnd + " 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
        }
        return queryWrapper;
    }
}
