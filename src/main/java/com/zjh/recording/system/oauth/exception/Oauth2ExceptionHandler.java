package com.zjh.recording.system.oauth.exception;

import com.zjh.recording.system.common.exception.DefaultGlobalExceptionHandlerAdvice;
import com.zjh.recording.system.common.vo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理
 * @Author: ZJH
 * @Date: 2019/10/15 17:21
 */
@RestControllerAdvice
@Slf4j
public class Oauth2ExceptionHandler extends DefaultGlobalExceptionHandlerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder){

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("attribute",  "The Attribute");
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public Result usernameNotFoundException(UsernameNotFoundException ex) {
        log.error("UsernameNotFoundException:{}", ex.getMessage());
        return Result.fail(AuthorizationExceptionEnums.NOT_EXIST_USER_ACCOUNT);
    }

    @ResponseBody
    @ExceptionHandler(OAuth2Exception.class)
    public Result oAuth2Exception(OAuth2Exception e) {
        return Result.fail(AuthorizationExceptionEnums.NOT_EXIST_USER_ACCOUNT);
    }

    @ResponseBody
    @ExceptionHandler(InvalidGrantException.class)
    public Result invalidGrantException(InvalidGrantException e) {
        return Result.fail(AuthorizationExceptionEnums.NOT_EXIST_USER_ACCOUNT);
    }

    @ResponseBody
    @ExceptionHandler(ExpiredJwtException.class)
    public Result expiredJwtException(ExpiredJwtException e) {
        return Result.fail(AuthorizationExceptionEnums.NOT_EXIST_USER_ACCOUNT);
    }
}
