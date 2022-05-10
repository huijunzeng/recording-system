package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 笔记链接表 实体类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("note_link")
public class NoteLinkEntity extends BaseEntity {


    /**
     * 交易记录id
     */
    @TableField("transaction_record_id")
    private Long transactionRecordId;

    /**
     * 笔记链接url
     */
    @TableField("url")
    private String url;

    /**
     * 点赞数
     */
    @TableField("点赞数")
    private Integer likes;

    /**
     * 平台 1小红书
     */
    @TableField("platform")
    private Integer platform;


}
