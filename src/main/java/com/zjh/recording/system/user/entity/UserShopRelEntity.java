package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 用户店铺关联表 实体类
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_shop_rel")
public class UserShopRelEntity extends BaseEntity {

        /**
         * id主键
         */
        @TableId(type = IdType.ASSIGN_ID)
        private Long id;

        /**
         * 用户id
         */
        @TableField("user_id")
        private Long userId;

        /**
         * 店铺id
         */
        @TableField("shop_id")
        private Long shopId;


        }
