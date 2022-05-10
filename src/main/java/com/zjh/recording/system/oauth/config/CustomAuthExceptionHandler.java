package com.zjh.recording.system.oauth.config;

import com.zjh.recording.system.common.exception.SystemExceptionEnums;
import com.zjh.recording.system.common.util.JSONUtil;
import com.zjh.recording.system.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义未授权 token无效 权限不足返回信息处理类
 * @Author: ZJH
 * @Date: 2020/6/19 17:39
 */

@Component
@Slf4j
public class CustomAuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    /**
     * AuthenticationEntryPoint
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Throwable cause = authException.getCause();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // CORS "pre-flight" request
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        if (cause instanceof InvalidTokenException) {
            log.error("InvalidTokenException : {}",cause.getMessage());
            // Token无效
            response.getWriter().write(JSONUtil.objectToJson(Result.fail(SystemExceptionEnums.INVALID_TOKEN)));
        } else {
            log.error("Unauthorized : Unauthorized");
            // 资源未授权
            response.getWriter().write(JSONUtil.objectToJson(Result.fail(SystemExceptionEnums.UN_AUTHORIZED)));
        }
    }

    /**
     * AccessDeniedHandler
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        //访问资源的用户权限不足
        log.error("AccessDeniedException : {}", accessDeniedException.getMessage());
        response.getWriter().write(JSONUtil.objectToJson(Result.fail(HttpStatus.UNAUTHORIZED.value(), accessDeniedException.getMessage())));
    }
}
