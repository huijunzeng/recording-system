package com.zjh.recording.system.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:  性别（ F女 M男 O其他）
 * @Author: ZJH
 * @Date: 2021/1/19 23:08
 */
@Getter
@AllArgsConstructor
public enum UserSexEnum {

    FEMALE("F", "女"),
    MALE("M", "男"),
    OTHER("O", "其他"),
    ;

    private String code;
    private String msg;

}
