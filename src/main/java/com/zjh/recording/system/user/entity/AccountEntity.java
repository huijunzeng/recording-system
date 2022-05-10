package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 账号信息表 实体类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("account")
public class AccountEntity extends BaseEntity {


    /**
     * 小红书号
     */
    @TableField("xhs_id")
    private String xhsId;

    /**
     * 小红书昵称
     */
    @TableField("xhs_nickname")
    private String xhsNickname;

    /**
     * 小红书粉丝数
     */
    @TableField("xhs_fans")
    private String xhsFans;

    /**
     * 微信号
     */
    @TableField("wechat_id")
    private String wechatId;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 微博
     */
    @TableField("weibo")
    private String weibo;

    /**
     * 合作级别 待定
     */
    @TableField("level")
    private Integer level;

    /**
     * 回复标记 0否 1是
     */
    @TableField("reply_status")
    private Integer replyStatus;

    /**
     * 收费标准
     */
    @TableField("charging_standard")
    private String chargingStandard;


}
