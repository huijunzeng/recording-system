package com.zjh.recording.system.common.vo;

import com.zjh.recording.system.common.exception.ExceptionType;
import com.zjh.recording.system.common.exception.SystemExceptionEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应体封装类
 * @author zjh
 * @Date: 2021/1/2 13:10
 */
@Data
@ApiModel(value = "统一响应体封装类")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应信息描述")
    private String msg;

    //JsonInclude.Include.NON_NULL--null结果不返回
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "响应数据体")
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *
     * @param exceptionType 业务操作响应码
     */
    public Result(ExceptionType exceptionType) {
        this(exceptionType.getCode(), exceptionType.getMsg(), null);
    }

    public Result(ExceptionType exceptionType, T data) {
        this(exceptionType.getCode(), exceptionType.getMsg(), data);
    }

    public Result(ExceptionType exceptionType, String msg) {
        this(exceptionType.getCode(), msg);
    }

    public Result(ExceptionType exceptionType, String msg, T data) {
        this(exceptionType.getCode(), msg, data);
    }

    public Result(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> Result<T> data(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> data(String msg, T data) {
        return new Result<>(SystemExceptionEnums.SYSTEM_SUCCESS, msg, data);
    }

    public static <T> Result<T> data(T data) {
        return new Result<>(SystemExceptionEnums.SYSTEM_SUCCESS, data);
    }

    public static <T> Result<T> success(ExceptionType exceptionType) {
        return new Result<>(exceptionType);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SystemExceptionEnums.SYSTEM_SUCCESS, data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(SystemExceptionEnums.SYSTEM_SUCCESS, msg);
    }

    public static <T> Result<T> fail(ExceptionType exceptionType) {
        return new Result<>(exceptionType);
    }

    public static <T> Result<T> fail(ExceptionType exceptionType, String msg) {
        return new Result<>(exceptionType.getCode(), msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(SystemExceptionEnums.SYSTEM_FAILURE, msg);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * @param flag 成功状态
     * @param <T>  T 泛型标记
     * @return
     */
    public static <T> Result<T> status(boolean flag) {
        return flag ? success(SystemExceptionEnums.SYSTEM_SUCCESS) : fail(SystemExceptionEnums.SYSTEM_FAILURE);
    }
}