package com.zjh.recording.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(value = "交易记录新增")
@Data
public class TransactionRecordSaveDTO {

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("账号id")
    private Long accountId;

    @ApiModelProperty("支付订单号")
    private String orderId;

    @ApiModelProperty("合作模式 1送拍 2寄拍")
    private Integer mode;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("件数")
    private Integer count;

    @ApiModelProperty("订单支付方式 1全额立返 2部分立返 3收货立返")
    private Integer paymentType;

    @ApiModelProperty("立返金额")
    private BigDecimal coverAmount;

    @ApiModelProperty("商品编号")
    private String goodsNos;

    @ApiModelProperty("约定佣金")
    private BigDecimal agreedCommission;

    @ApiModelProperty("实付佣金")
    private BigDecimal actualCommission;

    @ApiModelProperty("状态 1下单待收货 2收货待出笔记 3出笔记结算佣金")
    private Integer status;

    @ApiModelProperty("是否需要汇集 1全额立返 2部分立返 3收货立返")
    private Integer returnFlag;

    @ApiModelProperty("回寄邮费")
    private BigDecimal returnPostage;

    @ApiModelProperty("总支出")
    private BigDecimal totalExpenditure;

    @ApiModelProperty("备注")
    private String remark;

}
