package com.zjh.recording.system.oauth.exception;

import com.zjh.recording.system.common.exception.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 继承ExceptionType接口方法
@Getter
@AllArgsConstructor
public enum AuthorizationExceptionEnums implements ExceptionType {

    INVALID_REQUEST(2000, "无效请求"),
    INVALID_CLIENT(2001, "无效client_id"),
    INVALID_GRANT(2002, "密码错误"),
    INVALID_SCOPE(2003, "无效scope"),
    INVALID_TOKEN(2004, "无效token"),
    INSUFFICIENT_SCOPE(2005, "授权不足"),
    REDIRECT_URI_MISMATCH(2006, "redirect url不匹配"),
    ACCESS_DENIED(2007, "拒绝访问"),
    METHOD_NOT_ALLOWED(2008, "不支持该方法"),
    SERVER_ERROR(2009, "权限服务错误"),
    UNAUTHORIZED_CLIENT(2010, "未授权客户端"),
    UNAUTHORIZED(2011, "未授权"),
    UNSUPPORTED_RESPONSE_TYPE(2012, " 支持的响应类型"),
    UNSUPPORTED_GRANT_TYPE(2013, "不支持的授权类型"),
    NOT_EXIST_USER_ACCOUNT(2014,"该账号不存在"),
    EXPIRED_TOKEN(2015,"token已过期，请重新登录"),
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
