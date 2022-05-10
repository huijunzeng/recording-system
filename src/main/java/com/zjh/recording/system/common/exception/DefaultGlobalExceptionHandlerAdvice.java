package com.zjh.recording.system.common.exception;

import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import com.zjh.recording.system.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.*;

/**
 * 全局异常处理类  按顺序优先处理
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    //@ResponseStatus(HttpStatus.FORBIDDEN)  这里指的是请求头的status Code会被设置为403

    /**
     * AccessDeniedException 拒绝访问异常
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public Result badMethodExpressException(AccessDeniedException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return Result.fail(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    /**
     * methodArgumentNotValidException 参数检验异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("handleMethodArgumentNotValidException start, uri:{}, caused by: {} ", request.getRequestURI(), ex);
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        Set<String> errorMsgSet = new HashSet<>();
        Iterator<FieldError> iterator = fieldErrorList.iterator();
        while(iterator.hasNext()) {
            FieldError fieldError = iterator.next();
            errorMsgSet.add(fieldError.getDefaultMessage());
        }
        return Result.fail(SystemExceptionEnums.PARAM_VALID_ERROR, CollectionUtils.isEmpty(errorMsgSet) ? SystemExceptionEnums.PARAM_VALID_ERROR.getMsg() : StringUtils.join(errorMsgSet, ", "));
    }

    /**
     * missingServletRequestParameterException 参数绑定异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.PARAM_BIND_ERROR);
    }

    /**
     * noHandlerFoundException 请求找不到异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public Result noHandlerFoundException(NoHandlerFoundException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return Result.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * HttpRequestMethodNotSupportedException 方法请求类型异常
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return Result.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    /**
     * MultipartException 文件上传异常
     */
    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.UPLOAD_FILE_SIZE_LIMIT);
    }

    /**
     * DuplicateKeyException 唯一键冲突
     */
    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.DUPLICATE_PRIMARY_KEY);
    }

    /**
     * ArithmeticException 算法异常
     */
    @ExceptionHandler(value = {ArithmeticException.class})
    public Result arithmeticException(ArithmeticException ex) {
        log.error("arithmeticException:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.ARITHMETIC_ERROR);
    }

    /**
     * IllegalArgumentException 非法参数
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Result illegalArgumentException(IllegalArgumentException ex) {
        log.error("illegalArgumentException:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.PARAM_VALID_ERROR, ex.getMessage());
    }

    /**
     * SQLException sql异常处理
     */
    @ExceptionHandler({SQLException.class})
    public Result SQLException(SQLException ex) {
        log.error("SQLException:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.PARAM_VALID_ERROR, ex.getMessage());
    }

    /**
     * ConstraintViolationException 单个参数校验
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException ex){
        log.error("constraintViolationException:{}", ex.getMessage());
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations
             ) {
            msg.append(", ").append(constraintViolation.getMessage());
        }
        return Result.fail(SystemExceptionEnums.PARAM_VALID_ERROR, msg == null ? SystemExceptionEnums.PARAM_VALID_ERROR.getMsg() : msg.substring(2));
    }

    /**
     * ExcelExportException EasyPOI导出异常
     */
    @ExceptionHandler(ExcelExportException.class)
    public Result excelExportException(ExcelExportException ex) {
        log.error("ExcelExportException:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.SYSTEM_FAILURE, ex.getMessage());
    }

    /**
     * NoSuchElementException 空文件异常
     */
    @ExceptionHandler(NoSuchElementException.class)
    public Result noSuchElementException(NoSuchElementException ex) {
        log.error("NoSuchElementException:{}", ex.getMessage());
        return Result.fail(SystemExceptionEnums.EMPTY_FILE_ERROR);
    }

    /**
     * BusinessException 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(BusinessException ex) {
        log.error("businessException:{}", ex.getMessage());
        return Result.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 其他未知异常
     * 返回状态码:500
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception ex) {
        log.error("exception:{}", ex);
        return Result.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

    /**
     * 其他未知异常
     * 返回状态码:500
     */
    @ExceptionHandler(value = {Throwable.class})
    public Result throwable(Throwable throwable) {
        log.error("throwable:{}", throwable.getMessage());
        return Result.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

}