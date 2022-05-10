package com.zjh.recording.system.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zjh
 * @Description 逻辑枚举类
 * @date 2021/01/11 11:08
 */
@Getter
@AllArgsConstructor
public enum LogicEnum {

    NO(0,"否"),
    YES(1,"是");

    /**
     * 类型码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;
}
