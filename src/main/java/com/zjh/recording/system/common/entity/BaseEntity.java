package com.zjh.recording.system.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础类
 *
 * @Author: ZJH
 * @Date: 2020/1/14 10:21
 */

@Data
@ApiModel(value = "实体类基础类")
public class BaseEntity implements Serializable {

    public static final String ID = "id";
    public static final String CREATED_BY = "createdBy";
    public static final String CREATED_USERNAME = "createdUsername";
    public static final String UPDATED_BY = "updatedBy";
    public static final String UPDATED_USERNAME = "updatedUsername";
    public static final String CREATED_TIME = "createdTime";
    public static final String UPDATED_TIME = "updatedTime";
    public static final String DELETED = "deleted";
    public static final String REMARK = "remark";
    public static final String VERSION = "version";

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     *
     * @TableField FieldFill.INSERT==插入时填充
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     *
     * @TableField FieldFill.INSERT_UPDATE==插入/更新时填充
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 创建人id
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建人名字
     */
    @TableField(value = "created_username", fill = FieldFill.INSERT)
    private String createdUsername;

    /**
     * 更新人id
     */
    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;

    /**
     * 更新人名字
     */
    @TableField(value = "updated_username", fill = FieldFill.INSERT_UPDATE)
    private String updatedUsername;

    /**
     * 是否已删除 1已删除 0未删除
     *
     * @TableLogic 逻辑删除，调用delete方法实际执行update
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    private Integer deleted;

    /**
     * 备注
     */
    @TableField(value = "remark", fill = FieldFill.INSERT_UPDATE)
    private String remark;

    /**
     * 版本号  每次操作版本加1
     */
    @TableField(update = "%s+1", fill = FieldFill.INSERT_UPDATE)
    private Long version;
}
