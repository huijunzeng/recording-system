package com.zjh.recording.system.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zjh.recording.system.common.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "账号信息vo实体类")
@Data
public class AccountVO extends BaseVO {

    @ApiModelProperty("id主键")
    private Long id;

    @ApiModelProperty("小红书号")
    private String xhsId;

    @ApiModelProperty("小红书号")
    private String xhsNickname;

    @ApiModelProperty("小红书粉丝数")
    private String xhsFans;

    @ApiModelProperty("微信号")
    private String wechatId;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("微博")
    private String weibo;

    @ApiModelProperty("合作级别")
    private Integer level;

    @ApiModelProperty("回复标记 0否 1是")
    private Integer replyStatus;

    @ApiModelProperty("收费标准")
    private String chargingStandard;

    @TableField("备注")
    private String remark;
}
