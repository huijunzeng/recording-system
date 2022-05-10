package com.zjh.recording.system.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@ApiModel("分页查询基础类")
@Data
public class PageQueryParam implements Serializable {

    @ApiModelProperty("页码")
    @Min(value = 1, message = "页码不能小于1")
    private int pageNum = 1;

    @ApiModelProperty("页大小")
    @Min(value = 1, message = "页大小不能小于1")
    private int pageSize = 10;

    /**
     * 封装mybatisplus的Page类
     * @return
     */
    public Page getPage() {
        return new Page(this.getPageNum(), this.getPageSize());
    }
}
