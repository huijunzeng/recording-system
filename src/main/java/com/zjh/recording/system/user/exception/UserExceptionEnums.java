package com.zjh.recording.system.user.exception;

import com.zjh.recording.system.common.exception.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 继承ExceptionType接口方法
@Getter
@AllArgsConstructor
public enum UserExceptionEnums implements ExceptionType {

    USER_ACCOUNT_LOCKED(3000,"该用户已被锁定，请联系管理员"),
    DUPLICATE_USERNAME(3001,"已存在该用户名"),
    DUPLICATE_ROLE_NAME(3002,"已存在该角色"),
    DUPLICATE_RESOURCE_NAME(3003,"已存在该资源"),
    FORBID_DELETE_BINDED_ROLE(3004,"该角色下已有用户，无法删除"),
    OLDPASSWORD_WRONG(3005,"旧密码输入有误"),
    ;

    /**
     * 错误类型码
     */
    private Integer code;

    /**
     * 错误类型描述信息
     */
    private String msg;

}
