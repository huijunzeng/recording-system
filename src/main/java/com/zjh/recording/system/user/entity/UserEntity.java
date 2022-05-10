package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 后台用户表 实体类
 *
 * @Author: ZJH
 * @Date: 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 性别（ F 女 M 男 O 其他）
     */
    @TableField("sex")
    private String sex;

    /**
     * 密码密文
     */
    @TableField("password")
    private String password;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 状态 0正常 1锁定
     */
    @TableField("status")
    private Integer status;

}
