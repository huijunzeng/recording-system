package com.zjh.recording.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjh.recording.system.common.entity.BaseEntity;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 交易记录表 实体类
 * @Author: zjh
 * @Date: 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("transaction_record")
public class TransactionRecordEntity extends BaseEntity {


        /**
         * 店铺id
         */
        @TableField("shop_id")
        private Long shopId;

        /**
         * 账号id
         */
        @TableField("account_id")
        private Long accountId;

        /**
         * 支付订单号
         */
        @TableField("order_id")
        private String orderId;

        /**
         * 合作模式 1送拍 2寄拍
         */
        @TableField("mode")
        private Integer mode;

        /**
         * 订单金额
         */
        @TableField("order_amount")
        private BigDecimal orderAmount;

        /**
         * 件数
         */
        @TableField("count")
        private Integer count;

        /**
         * 订单支付方式 1全额立返 2部分立返 3收货立返
         */
        @TableField("payment_type")
        private Integer paymentType;

        /**
         * 立返金额
         */
        @TableField("cover_amount")
        private BigDecimal coverAmount;

        /**
         * 商品编号
         */
        @TableField("goods_nos")
        private String goodsNos;

        /**
         * 约定佣金
         */
        @TableField("agreed_commission")
        private BigDecimal agreedCommission;

        /**
         * 实付佣金
         */
        @TableField("actual_commission")
        private BigDecimal actualCommission;

        /**
         * 状态 1下单待收货 2收货待出笔记 3出笔记结算佣金
         */
        @TableField("status")
        private Integer status;

        /**
         * 是否需要汇集 1全额立返 2部分立返 3收货立返
         */
        @TableField("return_flag")
        private Integer returnFlag;

        /**
         * 回寄邮费
         */
        @TableField("return_postage")
        private BigDecimal returnPostage;

        /**
         * 总支出
         */
        @TableField("total_expenditure")
        private BigDecimal totalExpenditure;


        }
