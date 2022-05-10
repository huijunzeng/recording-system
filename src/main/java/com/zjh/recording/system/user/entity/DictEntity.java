package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Description: 字典表 实体类
 *
 * @Author: zjh
 * @Date: 2022-05-11
 */
@Data
@TableName("dict")
public class DictEntity {

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典编码
     */
    @TableField("code")
    private Long code;

    /**
     * 字典值
     */
    @TableField("value")
    private String value;

    /**
     * 备注
     */
    @TableField(value = "remark", fill = FieldFill.INSERT_UPDATE)
    private String remark;

    /**
     * 创建时间
     *
     * @TableField FieldFill.INSERT==插入时填充
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

}
