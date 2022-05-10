package com.zjh.recording.system.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zjh
 * @Description 系统业务异常枚举 继承ExceptionType接口方法
 * @date 2021/01/11 11:08
 */
@Getter
@AllArgsConstructor
public enum SystemExceptionEnums implements ExceptionType {

    SYSTEM_SUCCESS(0, "成功"),
    SYSTEM_FAILURE(1, "业务异常"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    UN_AUTHORIZED(401, "请求未授权"),
    INVALID_TOKEN(401, "无效token"),
    CLIENT_UN_AUTHORIZED(401, "客户端请求未授权"),
    REQ_REJECT(403, "请求被拒绝"),
    NOT_FOUND(404, "404 没找到请求"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),

    GATEWAY_NOT_FOUND_SERVICE(1002, "服务未找到"),
    GATEWAY_ERROR(1003, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(1004, "网关超时"),

    UPLOAD_FILE_SIZE_LIMIT(1005, "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY(1006,"唯一键冲突"),
    ARITHMETIC_ERROR(1007,"算法错误"),
    EMPTY_FILE_ERROR(1008,"文件不能为空"),
    IMPORT_EXCLE_FORMAT_ERROR(1009,"Excel文件格式有误"),

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
