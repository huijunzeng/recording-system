package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 店铺表 实体类
 *
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop")
public class ShopEntity extends BaseEntity {


    /**
     * 店铺名
     */
    @TableField("name")
    private String name;

    /**
     * 店铺url地址
     */
    @TableField("url")
    private String url;


}
